package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
		Book b1 = new Book("Evil under the sun", "Agatha Christie", 1941, "123-5879-987", 22);
		Book b2 = new Book("The raven", "Edgar Allan Poe", 1845, "123898-454646", 35.5);
		Book b3 = new Book("Great Expectations", "Charles Dickens", 1860, "9866-564-6546 ", 40);
		
		repository.save(b1);
		repository.save(b2);
		repository.save(b3);
	};
	}
}
