package com.younghun.library;

import com.younghun.library.model.Author;
import com.younghun.library.model.Book;
import com.younghun.library.model.Category;
import com.younghun.library.model.Publisher;
import com.younghun.library.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}


	@Bean
	public CommandLineRunner createDummyData(BookService bookService) {
		return(args) -> {
			Book book1 = new Book("Javaの基本","皆さんのJAVA言語", "978-5-6761-1876-1");
			Author author1 = new Author("れい","アンヨンフン");
			Category category1 = new Category("IT");
			Publisher publisher1 = new Publisher("CAL");
			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

			Book book2 = new Book("test2","test2", "test2");
			Author author2 = new Author("test2","test2");
			Category category2 = new Category("test2");
			Publisher publisher2 = new Publisher("test2");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);
		};
	}
}
