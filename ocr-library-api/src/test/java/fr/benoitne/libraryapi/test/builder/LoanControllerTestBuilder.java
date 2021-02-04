package fr.benoitne.libraryapi.test.builder;

import fr.benoitne.libraryapi.persistence.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanControllerTestBuilder {

    public Optional<UserEntity> getOptionalUserEntity(UserEntity userEntity){
        return Optional.ofNullable(userEntity);
    }

    public UserEntity getUserEntityTest (){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setRole("USER");
        userEntity.setAdress("50 rue de Corbeil, 91150 Saintry-sur-Seine");
        userEntity.setEmail("benoitnerin@gmail.com");
        userEntity.setFirstName("Benoît");
        userEntity.setLastName("Nérin");
        userEntity.setPassword("beornottobe");
        userEntity.setPhoneNumber("0671406428");
        userEntity.setUserName("Benoit");
        userEntity.setLoanEntity(getLoanEntityWithoutData());
        return userEntity;
    }

    public Optional<BookEntity> getOptionalBookEntity(BookEntity bookEntity){
        return Optional.ofNullable(bookEntity);
    }

    public BookEntity getBookEntityTest (){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(7);
        bookEntity.setQuantity(1);
        bookEntity.setAuthor("Lilian Almeras");
        bookEntity.setImageLink("https://zupimages.net/up/20/40/sz9q.jpg");
        bookEntity.setPageNumber(180);
        bookEntity.setPublishing("AMPHORA");
        bookEntity.setStatus("disponible");
        bookEntity.setTitle("Rugby Combat System");
        bookEntity.setType("Sport");
        bookEntity.setSummary("La violence dans le rugby et les graves traumatismes qui peuvent" +
                " en découler apparaissent aujourd hui comme le problème central de ce sport. " +
                "Les affrontements directs et les percussions sont en effet devenus des actions prépondérantes" +
                " du jeu moderne. Ces dernières années, la puissance des chocs, aux conséquences " +
                "parfois dramatiques, se retrouve accrue par la mise en confrontation d athlètes aux" +
                " gabarits et poids sans commune mesure. De plus, le jeu est souvent devenu stéréotypé," +
                " ennuyeux et laborieux, démuni de tout l esprit créatif qui l animait auparavant.");
        bookEntity.setLoanEntity(getLoanEntityWithoutData());
        bookEntity.setLibraryEntity(getLibraryEntityEmpty());
        bookEntity.setUserWaitingLine(new ArrayList<>());
        return bookEntity;
    }

    public LibraryEntity getLibraryEntityEmpty(){
        LibraryEntity libraryEntity = new LibraryEntity();
        return libraryEntity;
    }

    public Optional<LoanEntity> getOptionalLoanEntity(LoanEntity loanEntity){
        return Optional.ofNullable(loanEntity);
    }

    public LoanEntity getLoanEntityTest(){
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(1);
        loanEntity.setStatus("En cours de prêt");
        loanEntity.setEndBorrowingDate("2021-03-01T16:43:21.450");
        loanEntity.setProlongationDate(null);
        loanEntity.setStartBorrowingDate("2021-01-27T16:43:21.450");
        loanEntity.setBookEntity(getBookEntityTest());
        loanEntity.setUserEntity(getUserEntityTest());

        return loanEntity;
    }


    public List<LoanEntity> getLoanEntityWithoutData(){
        return new ArrayList<>();
    }

    public LoanArchiveEntity getLoanArchiveEntityWithoutData(){ return new LoanArchiveEntity();}
}
