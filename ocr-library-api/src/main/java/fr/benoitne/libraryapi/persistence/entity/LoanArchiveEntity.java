package fr.benoitne.libraryapi.persistence.entity;


import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class LoanArchiveEntity {

    @Id
    private long id;

    private String startBorrowingDate;

    private String endBorrowingDate;

    private String prolongationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookEntity_id")
    private BookEntity bookEntity;

    public LoanArchiveEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProlongationDate() {
        return prolongationDate;
    }

    public void setProlongationDate(String prolongationDate) {
        this.prolongationDate = prolongationDate;
    }

    public String getStartBorrowingDate() {
        return startBorrowingDate;
    }

    public void setStartBorrowingDate(String startBorrowingDate) {
        this.startBorrowingDate = startBorrowingDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public String getEndBorrowingDate() {
        return endBorrowingDate;
    }

    public void setEndBorrowingDate(String endBorrowingDate) {
        this.endBorrowingDate = endBorrowingDate;
    }

}
