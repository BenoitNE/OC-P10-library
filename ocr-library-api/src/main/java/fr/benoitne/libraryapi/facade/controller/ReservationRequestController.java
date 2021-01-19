package fr.benoitne.libraryapi.facade.controller;

import fr.benoitne.library.dto.ReservationRequestDTO;
import fr.benoitne.libraryapi.facade.assembler.ReservationrequestDTOAssembler;
import fr.benoitne.libraryapi.persistence.entity.UserEntity;
import fr.benoitne.libraryapi.persistence.repository.ReservationRequestRepository;
import fr.benoitne.libraryapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/")
public class ReservationRequestController {

    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Autowired
    private ReservationrequestDTOAssembler reservationrequestDTOAssembler;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/reservation-request")
    @ResponseBody
    public Stream<ReservationRequestDTO> allReservations(String userName){
        UserEntity userEntity = userRepository.getByUserName(userName);
        return StreamSupport.stream(userEntity.getReservationRequestEntities().spliterator(), false)
                .map(reservationRequestEntity -> reservationrequestDTOAssembler
                        .convertToDTO(reservationRequestEntity));
    }
}
