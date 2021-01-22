package fr.benoitne.libraryapi.facade.assembler;

import fr.benoitne.libraryapi.persistence.entity.LoanArchiveEntity;
import fr.benoitne.libraryapi.persistence.entity.LoanEntity;

public class LoanArchiveEntityBuilder {

    public LoanArchiveEntity getLoanArchiveEntity (LoanEntity loanEntity){
        LoanArchiveEntity loanArchiveEntity = new LoanArchiveEntity();
        loanArchiveEntity.setBookEntity(loanEntity.getBookEntity());
        loanArchiveEntity.setUserEntity(loanEntity.getUserEntity());
        loanArchiveEntity.setId(loanEntity.getId());
        loanArchiveEntity.setEndBorrowingDate(loanEntity.getEndBorrowingDate());
        loanArchiveEntity.setProlongationDate(loanEntity.getProlongationDate());
        loanArchiveEntity.setStartBorrowingDate(loanEntity.getStartBorrowingDate());
        return loanArchiveEntity;
    }
}
