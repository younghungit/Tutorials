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
	
	@Column(name = "isbn")
	private String ISBN;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "books_authors",
	joinColumns = {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="author_id")})
	private Set<Author> authors = new HashSet<>();


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="books_categories",
	joinColumns = {@JoinColumn(name="book_id")},
	inverseJoinColumns = {@JoinColumn(name="category_id")})
	private Set<Category> categories = new HashSet<>();

	

}
