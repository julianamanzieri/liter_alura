package com.julianamanzieri.literalura.api.LiterAlura;

import com.julianamanzieri.literalura.api.LiterAlura.principal.Principal;
import com.julianamanzieri.literalura.api.LiterAlura.repository.AuthorRepository;
import com.julianamanzieri.literalura.api.LiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(bookRepository, authorRepository);
		principal.displayMenu();
	}
}
