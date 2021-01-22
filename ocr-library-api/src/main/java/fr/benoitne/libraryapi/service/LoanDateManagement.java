package fr.benoitne.libraryapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.benoitne.libraryapi.persistence.repository.LoanRepository;
import org.springframework.stereotype.Service;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

import javax.swing.*;

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

	public String get48HWaitingDate() {
		LocalDateTime dateTime = LocalDateTime.parse(getStartBorrowingDate()).plusDays(2);
		return dateTime.toString();
	}

	public String getReturnDate(List<LoanEntity> loanEntities) {
		LocalDateTime returnDateA = null;
		LocalDateTime returnDateB = null;

		Stream<LoanEntity> loanEntityStream1 = loanEntities.stream();
		Stream<LoanEntity> loanEntityStream2 = loanEntities.stream();

		List<String> dateList1 = new ArrayList<>();
		List<String> dateList2 = new ArrayList<>();
		if (!loanEntities.isEmpty()) {

			dateList1 = loanEntityStream1.map(x -> x.getEndBorrowingDate()).collect(Collectors.toList());
			System.out.println("Liste taille de fin de prêt: "+ dateList1.size());

			dateList2 = loanEntityStream2.map(x -> x.getProlongationDate()).collect(Collectors.toList());
			System.out.println("Liste taille de prolongation: " + dateList2.size());


			for (int i = 0; i < dateList1.size(); i++) {
				if (returnDateA == null) {
					returnDateA = LocalDateTime.parse(dateList1.get(i));
				}
				if (dateList1.get(i) != null)
					if (returnDateA.compareTo(LocalDateTime.parse(dateList1.get(i))) < 0) {
						returnDateA = LocalDateTime.parse(dateList1.get(i));
					}
				try {
					if (returnDateB == null && dateList2.get(i) != null) {

						returnDateB = LocalDateTime.parse(dateList2.get(i));
					}
				} catch (Exception e) {
				}

				try {
					if (dateList2.get(i) != null)
						if (returnDateB.compareTo(LocalDateTime.parse(dateList2.get(i))) > 0) {
							returnDateB = LocalDateTime.parse(dateList2.get(i));
						}
				} catch (Exception e) {
				}
			}

			System.out.println(returnDateA + " et " + returnDateB);

			if (returnDateB != null) {
				if (returnDateA.isAfter(returnDateB) ) {
					System.out.println(returnDateB);
					return returnDateB.toString();

				}
				if (returnDateA.isBefore(returnDateB) && dateList1.size()>dateList2.size()) {
					System.out.println(dateList1.get(dateList1.size()-1));
					return dateList1.get(dateList1.size()-1);
				} else {
					System.out.println(returnDateB);
					return returnDateB.toString();
				}
			} else {
				System.out.println(returnDateA);
				return returnDateA.toString();

			}

		}
		return "Aucune date";
	}
//}

	public static void main(String[] args) {
		LoanDateManagement loanDateManagement = new LoanDateManagement();
		List<String> dateList1 = new ArrayList<>();
		Stream<String> dateList1Stream = dateList1.stream();
		dateList1.add("2020-03-22T17:55:20.180");
		dateList1.add("2020-03-24T17:55:20.180");
		//dateList1.add("2020-05-02T17:55:20.180");
		//dateList1.add("2020-12-05T17:55:20.180");
		//dateList1.add(null);

		List<String> dateList2 = new ArrayList<>();
		Stream<String> dateList2Stream = dateList2.stream();
		dateList2.add("2020-04-20T17:55:20.180");
		dateList2.add(null);
		//dateList2.add(null);
		//dateList2.add("2020-11-09T17:55:20.180");
		//dateList2.add("2020-01-12T17:55:20.180");

		List<String> dateList3 = new ArrayList<>();
		dateList3 = dateList1Stream.collect(Collectors.toList());
		System.out.println("dateList3 " + dateList3.size());

		List<String> dateList4 = new ArrayList<>();
		dateList4 = dateList2Stream.collect(Collectors.toList());
		System.out.println("dateList4 " + dateList4.size());

		LocalDateTime returnDateA = null;
		LocalDateTime returnDateB = null;

		for (int i = 0; i < dateList1.size(); i++) {
			if (returnDateA == null) {
				returnDateA = LocalDateTime.parse(dateList1.get(i));
			}
			if (dateList1.get(i) != null)
				if (returnDateA.compareTo(LocalDateTime.parse(dateList1.get(i))) < 0) {
					returnDateA = LocalDateTime.parse(dateList1.get(i));
				}
			try {
				if (returnDateB == null && dateList2.get(i) != null) {

					returnDateB = LocalDateTime.parse(dateList2.get(i));
				}
			} catch (Exception e) {
			}

			try {
				if (dateList2.get(i) != null)
					if (returnDateB.compareTo(LocalDateTime.parse(dateList2.get(i))) > 0) {
						returnDateB = LocalDateTime.parse(dateList2.get(i));
					}
			} catch (Exception e) {
			}
		}

		System.out.println(returnDateA + " et " + returnDateB);

			if (returnDateB != null) {
				if (returnDateA.isAfter(returnDateB) ) {
					System.out.println(returnDateB);
				}
				if (dateList2.get(dateList2.size()-1)==null && dateList1.size()>=dateList2.size()){
					System.out.println(dateList1.get(dateList1.size()-1));
				}
				else if (returnDateA.isBefore(returnDateB) && dateList1.size()>dateList2.size()) {
					System.out.println(dateList1.get(dateList1.size()-1));
				} else {
					System.out.println(returnDateB);
				}
			} else {
				System.out.println(returnDateA);

		}
	}
}

