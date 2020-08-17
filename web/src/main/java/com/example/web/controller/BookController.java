package com.example.web.controller;


import com.example.common.model.Book;
import com.example.common.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepo bookRepo;

    @GetMapping("/")
    public String bookPage(Model modelMap) {
        List<Book> books = bookRepo.findAll();
        modelMap.addAttribute("books", books);
        return "index";
    }
}

