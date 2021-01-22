package fr.benoitne.libraryapi.persistence.entity;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class ReservationRequestEntity {

    @Id
    private long id;

    private String status;

    private String startingDate;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "userEntity_id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "bookEntity_id")
    private BookEntity bookEntity;

    public ReservationRequestEntity() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }
}
