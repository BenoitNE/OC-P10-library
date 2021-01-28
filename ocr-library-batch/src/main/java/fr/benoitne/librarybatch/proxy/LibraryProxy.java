package fr.benoitne.librarybatch.proxy;


import java.util.List;
import java.util.stream.Stream;

import fr.benoitne.library.dto.ReservationRequestDTO;
import fr.benoitne.librarybatch.bean.BookBean;
import fr.benoitne.librarybatch.bean.ReservationRequestBean;
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

	@RequestMapping(method = RequestMethod.POST, path = "/book/48hwaiting")
	@ResponseBody
	public void waitingLine48HInit (@RequestParam(value = "bookId") long bookId);

	@RequestMapping(method = RequestMethod.POST, path = "/book/48hwaiting/remove")
	@ResponseBody
	public void reservationReturn(@RequestParam(value = "bookId") long bookId);

	@RequestMapping(method = RequestMethod.GET, path = "/all-reservation")
	@ResponseBody
	public List<ReservationRequestBean> allReservation();

	@RequestMapping(method = RequestMethod.GET, path = "/book")
	@ResponseBody
	public List<BookBean> listBooks();
}
