package fr.benoitne.librarybatch.schedule;

import fr.benoitne.librarybatch.bean.BookBean;
import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.bean.ReservationRequestBean;
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

@Component
public class MailLoan48HoursScheduler {


    @Autowired
    EmailTokenRepository emailTokenRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public LoanAPIConsumer loanAPIConsumer;

    @Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void sendEmailLoan48Hours() {

        SimpleMailMessage message = new SimpleMailMessage();
        List<BookBean>bookBeans=loanAPIConsumer.getBooksList48HFilter().collect(Collectors.toList());
        List<ReservationRequestBean> reservationRequestBeans = loanAPIConsumer.getReservationRequestBeanList();

        for (BookBean bookBean : bookBeans) {
            if (reservationRequestBeans.get(0).getUserDTO().getUserName().equals(bookBean.getUserWaitingLine().get(0))){
                email(message, reservationRequestBeans.get(0));
                loanAPIConsumer.getWaitingLine48HInit(bookBean.getId());
            }
        }
    }

        private void email(SimpleMailMessage message, ReservationRequestBean reservationRequestBean) {
            message.setTo(reservationRequestBean.getUserDTO().getEmail());
            message.setSubject(reservationRequestBean.getBookDTO().getTitle()+" est disponible");
            message.setText("Bonjour " + reservationRequestBean.getUserDTO().getUserName() + ", \n \n"
                    + "Nous vous informons que l'ouvrage " + reservationRequestBean.getBookDTO().getTitle()
                    + " est disponible. Vous avez 48h pour le récupérer. \n \n" + "Médiathèque CEC Yerres");

            LocalDateTime now = LocalDateTime.now();
            String date = now.toString();
           // EmailTokenEntity emailTokenEntity = new EmailTokenEntity("48h email", date, reservationRequestBean.getId());
           // emailTokenRepository.save(emailTokenEntity);

            this.emailSender.send(message);
        }

    }



