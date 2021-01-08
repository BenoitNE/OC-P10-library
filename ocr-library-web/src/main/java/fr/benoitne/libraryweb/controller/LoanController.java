package fr.benoitne.libraryweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import fr.benoitne.libraryweb.bean.LoanBean;
import fr.benoitne.libraryweb.proxy.LibraryProxy;

@Controller
public class LoanController {

	@Autowired
	private LibraryProxy feignProxy;

	@GetMapping(value = "/user/loan")
	public String goToLoans(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		List<LoanBean> loanBeans = feignProxy.listByUser(userName);
		model.addAttribute("loanBeans", loanBeans);
		return "loan";
	}

	@GetMapping(value = "/user/loan/{loanId}/extendDate")
	public String getExtendLoanDate(@PathVariable(value = "loanId") long id, Model model, HttpServletRequest request) {
		feignProxy.extendDate(id);
		return goToLoans(model, request);
	}

	@GetMapping(value = "/user/loan/historical") 
	public String goHistorical(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		List<LoanBean> loanBeans = feignProxy.listByUser(userName);
		model.addAttribute("loanBeans", loanBeans);
		return "historical";
	}

	@GetMapping(value = "/loan/{bookId}/add")
	public String addLoan (@PathVariable(value = "bookId") long bookId, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		long idUser = (long) session.getAttribute("id");
		feignProxy.newLoan(idUser,bookId);
		return goToLoans(model, request);
	}

}
