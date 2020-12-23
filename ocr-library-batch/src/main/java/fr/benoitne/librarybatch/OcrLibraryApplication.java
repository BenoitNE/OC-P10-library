package fr.benoitne.librarybatch;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients("fr.benoitne.librarybatch")
@EnableScheduling
public class OcrLibraryApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(OcrLibraryApplication.class, args);

	}

}
