package com.example.Bookstore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;






@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository,CategoryRepository crepository) {
	return (args) -> {
		log.info("save a couple of students");
		crepository.save(new Category("Crime"));
		crepository.save(new Category("Romance"));
		crepository.save(new Category("Mystery"));
		
		repository.save(new Book("Evil under the sun", "Agatha Christie", 1941, "123-5879-987", 22,crepository.findByName("Crime").get(0)));
		repository.save(new Book("The raven", "Edgar Allan Poe", 1845, "123898-454646", 35.5,crepository.findByName("Mystery").get(0)));
		repository.save(new Book("Great Expectations", "Charles Dickens", 1860, "9866-564-6546 ", 40,crepository.findByName("Romance").get(0)));
		
		
		log.info("fetch all students");
		for (Book book : repository.findAll()) {
			log.info(book.toString());
		}
	};
	}
}
