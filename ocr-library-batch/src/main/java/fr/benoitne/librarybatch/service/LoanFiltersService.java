package fr.benoitne.librarybatch.service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.benoitne.librarybatch.bean.LoanBean;
import fr.benoitne.librarybatch.proxy.LibraryProxy;

@Service
public class LoanFiltersService {

	@Autowired
	private LibraryProxy feignProxy;

	@Autowired
	private LoanComparingDatesService loanComparingDates;

	public Stream<LoanBean> getLoans() {
		List<LoanBean> loanBeans = feignProxy.allLoans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> loanComparingDates.mustBeReturned(x.getEndBorrowingDate(), x.getProlongationDate()) == true);
		
	}

}
