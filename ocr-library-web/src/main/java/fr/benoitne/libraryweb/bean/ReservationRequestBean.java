package fr.benoitne.libraryweb.bean;

import fr.benoitne.library.dto.BookDTO;
import fr.benoitne.library.dto.UserDTO;

public class ReservationRequestBean {

    private long id;

    private String status;

    private UserDTO userDTO;

    private BookDTO bookDTO;

    public ReservationRequestBean() {
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    @Override
    public String toString() {
        return "ReservationRequestBean{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", userDTO=" + userDTO +
                ", bookDTO=" + bookDTO +
                '}';
    }
}
