package fr.benoitne.libraryapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

@Service
public class LoanDateManagementService {

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

	public String get48HWaitingDate() {
		LocalDateTime dateTime = LocalDateTime.parse(getStartBorrowingDate()).plusDays(2);
		return dateTime.toString();
	}

	public String getReturnDate(List<LoanEntity> loanEntities) {

		Stream<LoanEntity> loanEntityStream1 = loanEntities.stream();
		Stream<LoanEntity> loanEntityStream2 = loanEntities.stream();

		List<String> dateListEndBorrowingDate = new ArrayList<>();
		List<String> dateListProlongationDate = new ArrayList<>();

		if (!loanEntities.isEmpty()) {

			dateListEndBorrowingDate = loanEntityStream1.map(x -> x.getEndBorrowingDate()).collect(Collectors.toList());
			dateListProlongationDate = loanEntityStream2.map(x -> x.getProlongationDate()).collect(Collectors.toList());


				if (dateListProlongationDate.get(0) == null) {
					return dateListEndBorrowingDate.get(0);
				}
				if (dateListProlongationDate.get(0) != null && dateListEndBorrowingDate.get(1) == null) {
					return dateListProlongationDate.get(0);
				}
				if (dateListProlongationDate.get(0) != null && dateListEndBorrowingDate.get(1) != null
						&& LocalDateTime.parse(dateListEndBorrowingDate.get(1))
						.isBefore(LocalDateTime.parse(dateListProlongationDate.get(0)))
						&& dateListProlongationDate.get(1) == null) {
					return dateListEndBorrowingDate.get(1);
				}else{
					return dateListProlongationDate.get(0);
				}
		}
		return null;
	}
}


