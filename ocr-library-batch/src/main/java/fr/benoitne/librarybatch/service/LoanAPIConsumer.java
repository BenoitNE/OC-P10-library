package fr.benoitne.librarybatch.service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.proxy.LibraryProxy;

@Service
public class LoanAPIConsumer {

	@Autowired
	private LibraryProxy feignProxy;

	@Autowired
	private LoanListFilterService loanListFilterService;

	public Stream<LoanBean> getLoansToReturn() {
		List<LoanBean> loanBeans = getLoanBeans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> loanListFilterService.getLoansWhoMustBeReturned(x.getEndBorrowingDate(), x.getProlongationDate()) == true);
		
	}

	public Stream<LoanBean> getLoans48Hours(){
		List<LoanBean> loanBeans = getLoanBeans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> loanListFilterService.getLoans48Hours(x.getBookDTO().getStatus()) == true);
	}

	private List<LoanBean> getLoanBeans() {
		return feignProxy.allLoans();
	}

/*	public static void main(String[] args) {
		LoanAPIConsumer loanAPIConsumer = new LoanAPIConsumer();
		Stream<LoanBean> loanBeanStream = loanAPIConsumer.getLoans48Hours();
		loanBeanStream.map(x -> x.getBookDTO()).forEach(System.out::println);
	}*/

}
