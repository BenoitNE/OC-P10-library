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
	private LoanListFilterService loanComparingDates;

	public Stream<LoanBean> getLoansToReturn() {
		List<LoanBean> loanBeans = feignProxy.allLoans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> loanComparingDates.getLoansWhoMustBeReturned(x.getEndBorrowingDate(), x.getProlongationDate()) == true);
		
	}

	public Stream<LoanBean> getLoans48Hours(){
		List<LoanBean> loanBeans = feignProxy.allLoans();
		return null;
	}

}
