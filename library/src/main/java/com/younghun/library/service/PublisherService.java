package com.younghun.library.service;


import com.younghun.library.model.Publisher;
import com.younghun.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findAllPublishers(){return publisherRepository.findAll();}
}
