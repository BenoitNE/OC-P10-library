package fr.benoitne.librarybatch.schedule;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.entity.EmailTokenEntity;
import fr.benoitne.librarybatch.repository.EmailTokenRepository;
import fr.benoitne.librarybatch.service.LoanAPIConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MailLoan48Hours {

    @Autowired
    LoanAPIConsumer loanFilters;

    @Autowired
    EmailTokenRepository emailTokenRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void sendEmailLoan48Hours() {

        SimpleMailMessage message = new SimpleMailMessage();
        List<LoanBean> loanBeans = loanFilters.getLoans48Hours().collect(Collectors.toList());

        for (LoanBean loanbean : loanBeans) {

            email(message, loanbean);
        }
    }

        private void email(SimpleMailMessage message, LoanBean loanbean) {
            message.setTo(loanbean.getUserDTO().getEmail());
            message.setSubject(loanbean.getBookDTO().getTitle()+" est disponible");
            message.setText("Bonjour " + loanbean.getUserDTO().getUserName() + ", \n \n"
                    + "Nous vous informons que l'ouvrage " + loanbean.getBookDTO().getTitle()
                    + " est disponible. Vous avez 48h pour le récupérer. \n \n" + "Médiathèque CEC Yerres");

            LocalDateTime now = LocalDateTime.now();
            String date = now.toString();
            EmailTokenEntity emailTokenEntity = new EmailTokenEntity("48h email", date, loanbean.getId());
            emailTokenRepository.save(emailTokenEntity);

            this.emailSender.send(message);
        }
    }



