package fr.benoitne.libraryweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.benoitne.libraryweb.bean.UserBean;
import fr.benoitne.libraryweb.proxy.LibraryProxy;



@Controller
public class UserController {

	@Autowired
	private LibraryProxy feignProxy;

	
	 @GetMapping(value={"/", "/login"})
	    public String login(){
	        return "login";
	    }

	@GetMapping(value = "/user/home")
	public String goHome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserBean userBean = feignProxy.loadUserByUsername(auth.getName());
		session.setAttribute("userName", userBean.getUserName());
		session.setAttribute("role", userBean.getRole());
		session.setAttribute("id", userBean.getId());
		return "home";
	}

	@GetMapping(value = "/logout")
	public String disconnected(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return login();
	}

}
