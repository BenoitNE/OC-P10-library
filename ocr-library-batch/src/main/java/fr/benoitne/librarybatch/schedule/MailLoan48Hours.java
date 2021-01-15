package fr.benoitne.librarybatch.schedule;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.service.LoanAPIConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class MailLoan48Hours {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    LoanAPIConsumer loanFilters;

    @Scheduled(cron = "*/10 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void sendEmailLoan48Hours(){
       // SimpleMailMessage message = new SimpleMailMessage();
        LoanAPIConsumer loanAPIConsumer = new LoanAPIConsumer();
        Stream<LoanBean> loanBeanStream = loanAPIConsumer.getLoans48Hours();
        loanBeanStream.map(x -> x.getBookDTO()).forEach(System.out::println);
    }


}
