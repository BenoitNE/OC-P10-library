package fr.benoitne.libraryapi.test;

import fr.benoitne.libraryapi.persistence.entity.LoanEntity;
import fr.benoitne.libraryapi.service.LoanDateManagementService;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class LoanDateManagementServiceTest {

    LoanDateManagementService loanDateManagementService = new LoanDateManagementService();


    @Test
    public void getProlongationDate(){
        Assert.assertEquals("2020-09-22T17:55:20.180",loanDateManagementService
                .getProlongationDate("2020-09-01T17:55:20.180"));
    }

    @Test
    public void getReturnDateWithOnlyEndBorrowingDateToCompare() {
        Assert.assertEquals("2020-09-17T17:55:20.180", loanDateManagementService
                .getReturnDate(dataForGetReturnDateWithOnlyEndBorrowingDateToCompare()));
    }

    private List<LoanEntity> dataForGetReturnDateWithOnlyEndBorrowingDateToCompare() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setId(1);
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate(null);

        LoanEntity loanEntity2 = new LoanEntity();
        loanEntity2.setId(2);
        loanEntity2.setEndBorrowingDate("2020-09-22T17:55:20.180");
        loanEntity2.setProlongationDate(null);

        LoanEntity loanEntity3 = new LoanEntity();
        loanEntity3.setId(3);
        loanEntity3.setEndBorrowingDate("2020-09-27T17:55:20.180");
        loanEntity3.setProlongationDate(null);

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);
        loanEntityList.add(loanEntity2);
        loanEntityList.add(loanEntity3);

        return loanEntityList;
    }

    @Test
    public void getReturnDateWithOnlyProlongationDateToCompare() {
        Assert.assertEquals("2020-10-17T17:55:20.180", loanDateManagementService
                .getReturnDate(dataForGetReturnDateWithOnlyProlongationDateToCompare()));
    }

    private List<LoanEntity> dataForGetReturnDateWithOnlyProlongationDateToCompare() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate("2020-10-17T17:55:20.180");

        LoanEntity loanEntity2 = new LoanEntity();
        loanEntity2.setEndBorrowingDate("2020-09-22T17:55:20.180");
        loanEntity2.setProlongationDate("2020-10-22T17:55:20.180");

        LoanEntity loanEntity3 = new LoanEntity();
        loanEntity3.setEndBorrowingDate("2020-09-27T17:55:20.180");
        loanEntity3.setProlongationDate("2020-10-27T17:55:20.180");

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);
        loanEntityList.add(loanEntity2);
        loanEntityList.add(loanEntity3);

        return loanEntityList;
    }

    @Test
    public void getReturnDateWithEndBorrowingDateBeforeProlongationDateToCompare() {
        Assert.assertEquals("2020-09-22T17:55:20.180", loanDateManagementService
                .getReturnDate(dataGetReturnDateWithEndBorrowingDateBeforeProlongationDateToCompare()));
    }

    private List<LoanEntity> dataGetReturnDateWithEndBorrowingDateBeforeProlongationDateToCompare() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate("2020-10-17T17:55:20.180");

        LoanEntity loanEntity2 = new LoanEntity();
        loanEntity2.setEndBorrowingDate("2020-09-22T17:55:20.180");
        loanEntity2.setProlongationDate(null);

        LoanEntity loanEntity3 = new LoanEntity();
        loanEntity3.setEndBorrowingDate("2020-09-27T17:55:20.180");
        loanEntity3.setProlongationDate(null);

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);
        loanEntityList.add(loanEntity2);
        loanEntityList.add(loanEntity3);

        return loanEntityList;
    }

    @Test
    public void getReturnDateWithOneLoanOnlyEndBorrowingDate() {
        Assert.assertEquals("2020-09-17T17:55:20.180", loanDateManagementService
                .getReturnDate(dataGetReturnDateWithOneLoanOnlyEndBorrowingDate()));
    }

    private List<LoanEntity> dataGetReturnDateWithOneLoanOnlyEndBorrowingDate() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate(null);

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);

        return loanEntityList;
    }

    @Test
    public void getReturnDateWithOneLoanOnlyProlongationDate() {
        Assert.assertEquals("2020-10-17T17:55:20.180", loanDateManagementService
                .getReturnDate(dataGetReturnDateWithOneLoanOnlyProlongationDate()));
    }

    private List<LoanEntity> dataGetReturnDateWithOneLoanOnlyProlongationDate() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate("2020-10-17T17:55:20.180");

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);

        return loanEntityList;
    }

    @Test
    public void getReturnDateWithEndBorrowingDateBeforeProlongationDateToCompareThirdLoan() {
        Assert.assertEquals("2020-09-27T17:55:20.180", loanDateManagementService
                .getReturnDate(dataGetReturnDateWithEndBorrowingDateBeforeProlongationDateToCompareThirdLoan()));
    }

    private List<LoanEntity> dataGetReturnDateWithEndBorrowingDateBeforeProlongationDateToCompareThirdLoan() {
        LoanEntity loanEntity1 = new LoanEntity();
        loanEntity1.setEndBorrowingDate("2020-09-17T17:55:20.180");
        loanEntity1.setProlongationDate("2020-10-17T17:55:20.180");

        LoanEntity loanEntity2 = new LoanEntity();
        loanEntity2.setEndBorrowingDate("2020-09-22T17:55:20.180");
        loanEntity2.setProlongationDate("2020-10-22T17:55:20.180");

        LoanEntity loanEntity3 = new LoanEntity();
        loanEntity3.setEndBorrowingDate("2020-09-27T17:55:20.180");
        loanEntity3.setProlongationDate(null);

        List<LoanEntity> loanEntityList = new ArrayList<>();
        loanEntityList.add(loanEntity1);
        loanEntityList.add(loanEntity2);
        loanEntityList.add(loanEntity3);

        return loanEntityList;
    }

    @Test
    public void testIfListLoanEntityIsNull() {
        Assert.assertEquals("Date de retour indisponible", loanDateManagementService.getReturnDate(dataForTestIfListLoanEntityIsNull()));
    }

    private List<LoanEntity> dataForTestIfListLoanEntityIsNull() {
        List<LoanEntity> loanEntityList = null;
        return loanEntityList;
    }


}
