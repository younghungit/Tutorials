package com.younghun.library.model;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private String name;
	public Author(String description, String name) {
		this.description = description;
		this.name = name;
	}

	@ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
	private Set<Book> books = new HashSet<Book>();
}
