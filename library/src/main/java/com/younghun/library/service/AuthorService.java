package com.younghun.library.service;

import com.younghun.library.model.Author;
import com.younghun.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findAuthorById(Long id){
        return authorRepository.findById(id).orElseThrow( () -> new RuntimeException("その著者はいません"));}

    public List<Author> findAllAuthors(){return authorRepository.findAll();}
    public void createAuthor(Author author){authorRepository.save(author);}
    public void updateAuthor(Author author){authorRepository.save(author);}
    public void deleteAuthorById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("その著者はいません"));
        authorRepository.deleteById(author.getId());}

}
