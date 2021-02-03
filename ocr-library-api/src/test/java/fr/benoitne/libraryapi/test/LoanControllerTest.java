package fr.benoitne.libraryapi.test;


import com.sun.istack.NotNull;
import fr.benoitne.library.dto.LoanDTO;
import fr.benoitne.libraryapi.facade.controller.LoanController;
import fr.benoitne.libraryapi.persistence.entity.*;
import fr.benoitne.libraryapi.persistence.repository.BookRepository;
import fr.benoitne.libraryapi.persistence.repository.LoanArchiveRepository;
import fr.benoitne.libraryapi.persistence.repository.LoanRepository;
import fr.benoitne.libraryapi.persistence.repository.UserRepository;

import fr.benoitne.libraryapi.test.builder.LoanControllerTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerTest {

    @Autowired
    private LoanController loanController;

    @Autowired
    private LoanControllerTestBuilder builder;

    @MockBean
    private BookRepository bookRepositoryMock;

    @MockBean
    private UserRepository userRepositoryMock;

    @MockBean
    private LoanRepository loanRepositoryMock;

    @MockBean
    private LoanArchiveRepository loanArchiveRepositoryMock;


    /* ******************* newLoan tests ************************ */
    @Test
    public void newLoanNominalScenario(){
        Long bookId = builder.getBookEntityTest().getId();
        Long userId = builder.getUserEntityTest().getId();
        List<LoanEntity>loanEntityList=builder.getLoanEntityWithoutData();
        UserEntity userEntity = builder.getUserEntityTest();
        BookEntity bookEntity = builder.getBookEntityTest();
        bookEntity.setUserWaitingLine(new ArrayList<>());

        when (this.bookRepositoryMock.findById(bookId)).thenReturn(builder.getOptionalBookEntity(bookEntity));
        when (this.userRepositoryMock.findById(userId)).thenReturn((builder.getOptionalUserEntity(userEntity)));
        when (this.loanRepositoryMock.findAll()).thenReturn(loanEntityList);

        LoanDTO loanDTO = loanController.newLoan(builder.getUserEntityTest().getId(),builder.getBookEntityTest().getId());

        Assert.assertEquals(1,loanDTO.getId());
    }

    @Test
    public void newLoanWhenTheLoanForTheBookIsAlreadyExist(){
        Long bookId = builder.getBookEntityTest().getId();
        Long userId = builder.getUserEntityTest().getId();
        List<LoanEntity>loanEntityList=builder.getLoanEntityWithoutData();
        UserEntity userEntity = builder.getUserEntityTest();
        BookEntity bookEntity = builder.getBookEntityTest();

        when (this.bookRepositoryMock.findById(bookId)).thenReturn(builder.getOptionalBookEntity(bookEntity));
        when (this.userRepositoryMock.findById(userId)).thenReturn((builder.getOptionalUserEntity(userEntity)));
        when (this.loanRepositoryMock.findAll()).thenReturn(loanEntityList);

        LoanDTO loanDTO = loanController.newLoan(builder.getUserEntityTest().getId(),builder.getBookEntityTest().getId());
        LoanDTO sameLoanDTO = loanController.newLoan(builder.getUserEntityTest().getId(),builder.getBookEntityTest().getId());

        Assert.assertEquals(1,sameLoanDTO.getId());

    }

    @Test(expected = Exception.class)
    public void newLoanExceptionTest(){
        loanController.newLoan(1,-33);
    }

    /* ******************* extendDate tests ************************ */
    @Test
    public void extendDateNominalScenario(){
    Long loanId = builder.getLoanEntityTest().getId();

    when (this.loanRepositoryMock.findById(loanId)).thenReturn(builder.getOptionalLoanEntity(builder.getLoanEntityTest()));
    Optional<LoanDTO> optionalLoanDTO=loanController.extendDate(loanId);
    LoanDTO loanDTO = optionalLoanDTO.get();

    Assert.assertEquals("2020-11-21T16:43:21.450", loanDTO.getProlongationDate());
    }


    /* ******************* loanReturn tests ************************ */
    @Test
    public void loanReturn(){
        Long loanId = builder.getLoanEntityTest().getId();

        when (this.loanRepositoryMock.findById(loanId)).thenReturn(builder.getOptionalLoanEntity(builder.getLoanEntityTest()));

        Assert.assertEquals("Le livre à bien été retourné.", loanController.loanReturn(loanId));
    }

    @Test
    public void loanReturnExceptionTest(){
        Long loanId = Long.valueOf(-33);

        when (this.loanRepositoryMock.findById(loanId)).thenReturn(null);
        Assert.assertEquals("Le livre ne peut pas être rendu.",loanController.loanReturn(loanId));

    }


}
