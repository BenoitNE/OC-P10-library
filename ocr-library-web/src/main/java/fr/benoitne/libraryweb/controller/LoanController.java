package fr.benoitne.libraryweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.benoitne.libraryweb.bean.ReservationRequestBean;
import fr.benoitne.libraryweb.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.benoitne.libraryweb.bean.LoanBean;
import fr.benoitne.libraryweb.proxy.LibraryProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoanController {

	@Autowired
	private LibraryProxy feignProxy;

	@Autowired
	private BookController bookController;

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
		List<ReservationRequestBean> reservationRequestBeans = feignProxy.allReservations(userName);
		List<LoanBean> loanBeans = feignProxy.listByUser(userName);
		model.addAttribute("loanBeans", loanBeans);
		model.addAttribute("reservations", reservationRequestBeans);
		return "historical";
	}

	@GetMapping(value = "/loan/{bookId}/add")
	public String addLoan (@RequestParam("search") String search, @PathVariable(value = "bookId") long bookId, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean userBean = feignProxy.loadUserByUsername((String) session.getAttribute("userName"));
		feignProxy.newLoan(userBean.getId(),bookId);
		return bookController.bookSearch(search,model,request);
	}

	@GetMapping(value = "/loan/{loanId}/return")
	public String removeLoan (@PathVariable(value = "loanId") long loanId, Model model, HttpServletRequest request){
		feignProxy.loanReturn(loanId);
		return goToLoans(model, request);
	}

}
