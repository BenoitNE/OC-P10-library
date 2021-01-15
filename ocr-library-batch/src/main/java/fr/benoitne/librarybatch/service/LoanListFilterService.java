package fr.benoitne.librarybatch.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LoanListFilterService {

	public boolean getLoansWhoMustBeReturned(String endBorrowingDate, String prolongationDate) {
		LocalDateTime endDate = LocalDateTime.parse(endBorrowingDate);
		LocalDateTime prolongDate;
		LocalDateTime now = LocalDateTime.now();

		if (prolongationDate==null) {
			if (now.compareTo(endDate) > 0) {
				return true;
			}
		}
		if (!prolongationDate.isEmpty()) {
			prolongDate = LocalDateTime.parse(prolongationDate);
			if (now.compareTo(prolongDate) > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean getLoans48Hours(String statusBook){
		if (statusBook=="en attente"){
			return true;
		}
		return false;
	}

//	public static void main(String[] args) {
//
//		LoanComparingDates compareDates = new LoanComparingDates();
//		boolean output = compareDates.mustBeReturned("2020-08-27T16:43:21.450", null);
//
//		System.out.println(output);
//
//	}
}
