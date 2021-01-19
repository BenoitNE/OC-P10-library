package fr.benoitne.libraryapi.facade.assembler;

import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.library.dto.ReservationRequestDTO;
import fr.benoitne.libraryapi.persistence.entity.LoanEntity;
import fr.benoitne.libraryapi.persistence.entity.ReservationRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationrequestAssembler {

    @Autowired
    UserDTOAssembler userDTOAssembler;

    @Autowired
    BookDTOAssembler bookDTOAssembler;

    public ReservationRequestDTO convertToDTO (ReservationRequestEntity reservationRequestEntity) {
        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
        reservationRequestDTO.setBookDTO(bookDTOAssembler.convertToDTO(reservationRequestEntity.getBookEntity()));
        reservationRequestDTO.setUserDTO(userDTOAssembler.convertToDTO(reservationRequestEntity.getUserEntity()));
        reservationRequestDTO.setId(reservationRequestEntity.getId());
        reservationRequestDTO.setStatus(reservationRequestEntity.getStatus());
        return null;
    }
}
