package com.younghun.library.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	public Publisher(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "publishers", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Book> books = new HashSet<Book>();
}
