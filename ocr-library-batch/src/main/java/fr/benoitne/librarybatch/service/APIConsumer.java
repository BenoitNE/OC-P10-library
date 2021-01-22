package fr.benoitne.librarybatch.service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import fr.benoitne.librarybatch.bean.BookBean;
import fr.benoitne.librarybatch.bean.ReservationRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.proxy.LibraryProxy;

@Service
public class APIConsumer {

	@Autowired
	private LibraryProxy feignProxy;

	@Autowired
	private ListFilterService listFilterService;

	public Stream<LoanBean> getLoansToReturn() {
		List<LoanBean> loanBeans = getLoanBeans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> listFilterService.getLoansWhoMustBeReturned(x.getEndBorrowingDate(), x.getProlongationDate()) == true);
	}

	public Stream<BookBean> getBooksList48HFilter(){
		List<BookBean>bookBeans=feignProxy.listBooks();
		return StreamSupport.stream(bookBeans.stream().spliterator(), false).filter(
				x -> listFilterService.booksFilter48H(x.getStatus()) == true);
	}

//	public Stream<LoanBean> getLoans48HoursStream(){
//		List<LoanBean> loanBeans = getLoanBeans();
//		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
//				x -> loanListFilterService.getLoans48Hours(x.getBookDTO().getStatus()) == true);
//	}

	public Stream<BookBean> getReservations48HoursStreamRemove(){
		List<BookBean> bookBeans = feignProxy.listBooks();
		return StreamSupport.stream(bookBeans.stream().spliterator(), false).filter(
				x -> listFilterService.getReservations48HoursToRemove(x.getStatus(), x.getReturnDate()) == true);
	}

	private List<LoanBean> getLoanBeans() {
		return feignProxy.allLoans();
	}

	public void getWaitingLine48HInit (long bookId){
		feignProxy.waitingLine48HInit(bookId);
	}
	
	public void removeWaitingLoan48H (long bookId){
		feignProxy.reservationReturn(bookId);
	}

	public List<ReservationRequestBean> getReservationRequestBeanList (){
		return feignProxy.allReservation();
	}



}
