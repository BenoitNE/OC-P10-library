package fr.benoitne.librarybatch.service;

import java.util.List;
import java.util.Optional;
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

	public Stream<LoanBean> getLoans48HoursStream(){
		List<LoanBean> loanBeans = getLoanBeans();
		return StreamSupport.stream(loanBeans.stream().spliterator(), false).filter(
				x -> loanListFilterService.getLoans48Hours(x.getBookDTO().getStatus()) == true);
	}

/*	public Optional <LoanBean> getLoan48HWaitingOptional (){
		Stream<LoanBean> loanBeans = getLoans48HoursStream();
		Optional<LoanBean> loanBean = loanBeans.findFirst();
		return loanBean;
	}*/

	private List<LoanBean> getLoanBeans() {
		return feignProxy.allLoans();
	}

	public void getWaitingLine48HInit (long loanId){
		feignProxy.waitingLine48HInit(loanId);
	}

}
