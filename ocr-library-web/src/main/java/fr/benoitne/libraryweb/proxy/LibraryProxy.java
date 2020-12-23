package fr.benoitne.libraryweb.proxy;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.benoitne.library.dto.UserDTO;
import fr.benoitne.libraryweb.bean.BookBean;
import fr.benoitne.libraryweb.bean.LoanBean;
import fr.benoitne.libraryweb.bean.UserBean;

@FeignClient(name = "ocr-library-api", url = "localhost:9090")
public interface LibraryProxy {

	@RequestMapping(method = RequestMethod.GET, path = "/book")
	@ResponseBody
	public List<BookBean> listBooks();

	@RequestMapping(method = RequestMethod.GET, path = "/book/search")
	@ResponseBody
	public List<BookBean> listSearch(@RequestParam("search") String search);

	@RequestMapping(method = RequestMethod.GET, path = "/user/authorization")
	@ResponseBody
	public UserBean getAuthorization(@RequestParam("userName") String userName,
			@RequestParam("password") String password);

	@RequestMapping(method = RequestMethod.GET, path = "/user/loan")
	@ResponseBody
	public List<LoanBean> listByUser(@RequestParam("userName") String userName);

	@RequestMapping(method = RequestMethod.GET, path = "/loan/{loanId}/extendDate")
	@ResponseBody
	LoanBean extendDate(@RequestParam(value = "loanId") long id);
	
	@RequestMapping(method = RequestMethod.GET, path = "/user/userName")
	@ResponseBody
	public UserBean loadUserByUsername(@RequestParam(value = "userName") String userName);
}
