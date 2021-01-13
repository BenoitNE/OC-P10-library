package fr.benoitne.librarybatch.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailLoan48Hours {

    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void sendEmailLoan48Hours(){
        SimpleMailMessage message = new SimpleMailMessage();

    }


}
