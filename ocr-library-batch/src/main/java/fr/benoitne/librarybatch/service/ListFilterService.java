package fr.benoitne.librarybatch.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class ListFilterService {

	public boolean getLoansWhoMustBeReturned(String endBorrowingDate, String prolongationDate) {
		LocalDateTime endDate = LocalDateTime.parse(endBorrowingDate);
		LocalDateTime prolongDate;
		LocalDateTime now = LocalDateTime.now();

		if (prolongationDate==null||prolongationDate.equals("Vous ne pouvez plus prolonger le prêt.")) {
			if (now.compareTo(endDate) > 0) {
				return true;
			}
		}
		if (prolongationDate!=null&&!prolongationDate.equals("Vous ne pouvez plus prolonger le prêt.")) {
			prolongDate = LocalDateTime.parse(prolongationDate);
			if (now.compareTo(prolongDate) > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean booksFilter48H(String statusBook){
		if (statusBook.equals("en attente NC")){
			return true;
		}
		return false;
	}

	public boolean getReservations48HoursToRemove(String statusBook, String waiting48HDate){
		waiting48HDate ="2020-10-31T17:55:20.180";
		if(waiting48HDate!=null) {
		LocalDateTime end48HDate = LocalDateTime.parse(waiting48HDate);
		LocalDateTime now = LocalDateTime.now();

			if (statusBook.equals("en attente 48h") && now.compareTo(end48HDate) > 0) {
				return true;
			}
		}
		return false;
	}



}
