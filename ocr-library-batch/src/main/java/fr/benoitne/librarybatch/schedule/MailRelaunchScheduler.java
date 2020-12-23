package fr.benoitne.librarybatch.schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.entity.EmailTokenEntity;
import fr.benoitne.librarybatch.repository.EmailTokenRepository;
import fr.benoitne.librarybatch.service.LoanFiltersService;

@Component
public class MailRelaunchScheduler {

	@Autowired
	LoanFiltersService loanFilters;

	@Autowired
	EmailTokenRepository emailTokenRepository;

	@Autowired
	public JavaMailSender emailSender;

	@Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")  
	/*
	 * second, minute, hour, day, month, weekday
	 */
	public void sendEmailRelaunch() {

		SimpleMailMessage message = new SimpleMailMessage();
		List<LoanBean> loanBeans = loanFilters.getLoans().collect(Collectors.toList());

		for (LoanBean loanbean : loanBeans) {

			email(message, loanbean);
		}

	}

	private void email(SimpleMailMessage message, LoanBean loanbean) {
		message.setTo(loanbean.getUserDTO().getEmail());
		message.setSubject("Retard de retour de prêt - Médiathèque CEC Yerres");
		message.setText("Bonjour " + loanbean.getUserDTO().getUserName() + ", \n \n"
				+ "Nous vous rappelons que vous avez l'ouvrage: " + loanbean.getBookDTO().getTitle()
				+ ", à nous retourner. \n \n" + "Médiathèque CEC Yerres");

		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		EmailTokenEntity emailTokenEntity = new EmailTokenEntity("Email relaunch", date, loanbean.getId());
		emailTokenRepository.save(emailTokenEntity);

		this.emailSender.send(message);
	}
		


}
