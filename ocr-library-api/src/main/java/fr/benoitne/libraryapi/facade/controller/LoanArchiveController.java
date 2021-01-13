/*
package fr.benoitne.libraryapi.facade.controller;

import fr.benoitne.library.dto.LoanArchiveDTO;
import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.libraryapi.persistence.repository.LoanArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/")
public class LoanArchiveController {

    @Autowired
    private LoanArchiveRepository loanArchiveRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/loan")
    @ResponseBody
    public Stream<LoanArchiveDTO> allLoans(){
        return StreamSupport.stream(loanArchiveRepository.findAll().spliterator(), false)
                .map(loanEntity -> loanDTOAssembler.convertToDTO(loanEntity));
    }
}
*/
