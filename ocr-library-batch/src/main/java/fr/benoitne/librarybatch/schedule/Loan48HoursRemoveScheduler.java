package fr.benoitne.librarybatch.schedule;

import fr.benoitne.librarybatch.bean.BookBean;
import fr.benoitne.librarybatch.bean.ReservationRequestBean;
import fr.benoitne.librarybatch.service.APIConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Loan48HoursRemoveScheduler {

    @Autowired
    public APIConsumer APIConsumer;


    @Scheduled(cron = "*/15 * * * * *")
//	@Scheduled(cron = "0 0 8 * * *")
    /*
     * second, minute, hour, day, month, weekday
     */
    public void removeWaitingLoan48H () {
        List<BookBean> bookBeans = APIConsumer.getReservations48HoursStreamRemove().collect(Collectors.toList());
        List<ReservationRequestBean> reservationRequestBeans = APIConsumer.getReservationRequestBeanList();

        for (BookBean bookBean : bookBeans) {
            if (reservationRequestBeans.get(0).getUserDTO().getUserName().equals(bookBean.getUserWaitingLine().get(0))){
                APIConsumer.removeWaitingLoan48H(bookBean.getId());
                System.out.println("Le prêt à bien été supprimé.");
            }
        }
    }

}
