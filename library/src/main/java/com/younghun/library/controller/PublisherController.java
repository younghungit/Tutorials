package com.younghun.library.controller;

import com.younghun.library.model.Publisher;
import com.younghun.library.service.AuthorService;
import com.younghun.library.service.BookService;
import com.younghun.library.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;



    @GetMapping("/publishers")
    public String findAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.findAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/delete-publisher/{id}")
    public String deletePublisher(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            publisherService.deletePublisher(id);  // Call service to delete the publisher
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "まずは関連書籍の削除をお願いします");
            return "redirect:/publishers";  // Redirect to the publishers list with the error
        }
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }

    @GetMapping("/update-publisher/{id}")
    public String updatepublisher(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.findPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher";
    }

    @PostMapping("/update-save-publisher/{id}")
    public String savepublisher(@PathVariable Long id, Publisher publisher,
                               BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "update-publisher";
        }
        publisherService.updatePublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Publisher publisher, Model model) {
        model.addAttribute("publisher", publisher);
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public String saveNewPublisher(Publisher publisher,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "add-publisher";
        }
        publisherService.createPublisher(publisher);
        return "redirect:/publishers";
    }
}
