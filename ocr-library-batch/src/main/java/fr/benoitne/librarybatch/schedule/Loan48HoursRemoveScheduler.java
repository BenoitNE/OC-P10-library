package fr.benoitne.librarybatch.schedule;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.service.LoanAPIConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Loan48HoursRemoveScheduler {

    @Autowired
    LoanAPIConsumer loanFilters;

    @Autowired
    public LoanAPIConsumer loanAPIConsumer;


    @Scheduled(cron = "*/15 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void removeWaitingLoan48H () {
        List<LoanBean> loanBeans = loanFilters.getLoans48HoursStreamRemove().collect(Collectors.toList());
        for (LoanBean loanbean : loanBeans) {
            if (loanbean.getUserDTO().getUserName().equals(loanbean.getBookDTO().getUserWaitingLine().get(0))){
                loanAPIConsumer.removeWaitingLoan48H(loanbean.getId());
                System.out.println("Le prêt à bien été supprimé.");
            }
        }
    }
    
}
