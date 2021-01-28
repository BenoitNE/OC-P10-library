package fr.benoitne.libraryapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.benoitne.libraryapi.exeption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

		if (loanEntities!=null) {
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
				if (loanEntities.size() == 1 && dateListProlongationDate.get(0) != null)
					return dateListProlongationDate.get(0);

				for (int i = 0; i < loanEntities.size(); i++) {
					if (loanEntities.size() > i + 1)
						if (dateListProlongationDate.get(i) != null && dateListEndBorrowingDate.get(i + 1) == null) {
							return dateListProlongationDate.get(i);
						}

					if (loanEntities.size() > i + 1)
						if (dateListProlongationDate.get(i) != null && dateListEndBorrowingDate.get(i) != null
								&& LocalDateTime.parse(dateListEndBorrowingDate.get(i))
								.isBefore(LocalDateTime.parse(dateListProlongationDate.get(i)))
								&& dateListProlongationDate.get(i + 1) == null) {
							return dateListEndBorrowingDate.get(i + 1);
						}
				}
				return dateListProlongationDate.get(0);
			}
		}
		return "Date de retour indisponible";
	}
}


