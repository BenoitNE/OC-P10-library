package fr.benoitne.libraryapi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

@Service
public class LoanDateManagement {

	public String getProlongationDate(String endBorrowingDate) {
		LocalDateTime dateTime = LocalDateTime.parse(endBorrowingDate).plusDays(21);
		String prolongationDate = dateTime.toString();
		return prolongationDate;
	}

	public Optional<LoanEntity> setLoanProlongationDate(Optional<LoanEntity> loanEntity) {
		String endBorrowingDate = loanEntity.map(x -> x.getEndBorrowingDate()).get();
		loanEntity.get().setProlongationDate(getProlongationDate(endBorrowingDate));
		return loanEntity;

	}

	public String getStartBorrowingDate() {
		LocalDateTime dateTime = LocalDateTime.now();
		return dateTime.toString();
	}

	public String getEndBorrowingDate() {
		LocalDateTime dateTime = LocalDateTime.parse(getStartBorrowingDate()).plusDays(21);
		return dateTime.toString();
	}

	public String get48HWaitingDate () {
		LocalDateTime dateTime = LocalDateTime.parse(getStartBorrowingDate()).plusDays(2);
		return dateTime.toString();
	}

}
