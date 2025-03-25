package com.younghun.library.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String isbn;



	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "books_authors",
	joinColumns = {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="author_id")})
	private Set<Author> authors = new HashSet<>();


	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="books_categories",
	joinColumns = {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="category_id")})
	private Set<Category> categories = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name="books_publishers",
			joinColumns = {@JoinColumn(name="book_id")},
			inverseJoinColumns = {@JoinColumn(name="publisher_id")})
	private Set<Publisher> publishers = new HashSet<>();

	public Book(String name, String description, String isbn) {
		this.name = name;
		this.description = description;
		this.isbn = isbn;
	}
	public void addPublisher(Publisher publisher){
		this.publishers.add(publisher);
		publisher.getBooks().add(this);
	}

	public void addAuthor(Author author){
		this.authors.add(author);
		author.getBooks().add(this);
	}

	public void addCategory(Category category){
		this.categories.add(category);
		category.getBooks().add(this);
	}
	

}
