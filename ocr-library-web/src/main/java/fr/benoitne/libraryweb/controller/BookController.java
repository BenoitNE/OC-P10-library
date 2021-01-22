package fr.benoitne.libraryweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.benoitne.libraryweb.bean.BookBean;
import fr.benoitne.libraryweb.proxy.LibraryProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookController {

	@Autowired
	private LibraryProxy feignProxy;
	
	@GetMapping(value = "/user/book-catalogue")
	public String bookCatalogue (Model model) {
		List<BookBean> bookBeans = feignProxy.listBooks();
		model.addAttribute("bookBeans", bookBeans);
		return "book-catalogue-test";
			
	}
	
	@GetMapping(value = "/user/book-search")
	public String bookSearch (@RequestParam("search") String search, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<BookBean> bookBeans = feignProxy.listSearch(search);
		model.addAttribute("booksSearch", bookBeans);
		
		return "book-search";
	}

	
}
