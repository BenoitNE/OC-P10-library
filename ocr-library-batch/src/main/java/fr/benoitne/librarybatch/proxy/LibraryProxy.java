package fr.benoitne.librarybatch.proxy;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.librarybatch.bean.LoanBean;



@FeignClient(name = "ocr-library-api", url = "localhost:9090")
public interface LibraryProxy {

	@RequestMapping(method = RequestMethod.GET, path = "/loan")
	@ResponseBody
	public List<LoanBean> allLoans();

	@RequestMapping(method = RequestMethod.POST, path = "/loan/48hwaiting")
	@ResponseBody
	public void waitingLine48HInit (@RequestParam(value = "loanId") long loanId);

	@RequestMapping(method = RequestMethod.POST, path = "/loan/return")
	@ResponseBody
	public void loanReturn (@RequestParam(value = "loanId") long loanId);
}
