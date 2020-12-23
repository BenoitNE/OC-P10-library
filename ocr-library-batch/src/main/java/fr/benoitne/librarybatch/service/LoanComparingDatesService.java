package fr.benoitne.librarybatch.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class LoanComparingDatesService {

	public boolean mustBeReturned(String endBorrowingDate, String prolongationDate) {
		LocalDateTime endDate = LocalDateTime.parse(endBorrowingDate);
		LocalDateTime prolongDate;
		LocalDateTime now = LocalDateTime.now();

		if (prolongationDate.isEmpty()) {
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

//	public static void main(String[] args) {
//
//		LoanComparingDates compareDates = new LoanComparingDates();
//		boolean output = compareDates.mustBeReturned("2020-08-27T16:43:21.450", null);
//
//		System.out.println(output);
//
//	}
}
