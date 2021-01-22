package fr.benoitne.libraryapi.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

@Service
public class SetLoanStatus {

	public LoanEntity initialStatus(LoanEntity loanEntity) {
		loanEntity.setStatus("En cours ou demande de prêt");
		return loanEntity;
	}
	
	public Optional<LoanEntity> prolongationStatus(Optional<LoanEntity> loanEntity) {
		loanEntity.get().setStatus("Prêt prolongé");
		return loanEntity;
	}
	
	public LoanEntity finalStatus(LoanEntity loanEntity) {
		loanEntity.setStatus("Livre rendu");
		return loanEntity;
	}
	
	public String loanInProgress () {
		return ("En cours de prêt");
	}

}
