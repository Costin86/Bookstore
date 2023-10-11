package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bookstore.domain.AppUser;
import com.example.Bookstore.domain.AppUserRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import org.junit.jupiter.api.Test;
//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTests {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;
    
    @Autowired
    private AppUserRepository urepository;
    
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Evil under the sun");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Agatha Christie");
    }
    
    @Test
    public void createNewBook() {
    	Category category = new Category("Children");
    	crepository.save(category);
    	Book book = new Book("Mickey Mouse", "Disney", 1945,"156649490", 39.90, category);
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    @Test
    public void deleteNewBook() {
		List<Book> books = repository.findByTitle("The raven");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks= repository.findByTitle("The raven");
		assertThat(newBooks).hasSize(0);
     }
    @Test
    public void createNewUser() {
    	AppUser user = new AppUser("costin","$2a$04$ei8ezXyK8LuXOfWOBi39CeKj3mVuOMtM6PiIaxbcvuoSSYfq05U3S", "costin");
    	urepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }  
    @Test
    public void findByNameShouldReturnUser() {
        AppUser user = urepository.findByUsername("admin");
        
        assertThat(user).isNotNull();
        assertThat(user.getPasswordHash()).isEqualTo("$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C");
    }
}
