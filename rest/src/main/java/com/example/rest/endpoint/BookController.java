package com.example.rest.endpoint;



import com.example.common.exception.ResourceNotFoundException;
import com.example.common.model.Book;
import com.example.common.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepo bookRepository;


    @PostMapping("/books")
    public Book deleteBook(@RequestBody Book book) {
        if (book.getId() > 0) {
            throw new RuntimeException("id ka");
        }
        return bookRepository.save(book);
    }



    @GetMapping("/books")
    public List<Book> getBook() {
        return bookRepository.findAll();
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book book, @PathVariable("id") int id) {
        Book bookFromDB = bookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("book dose not exist"));
        bookFromDB.setTitle(book.getTitle());
        bookFromDB.setPrice(book.getPrice());
        bookFromDB.setDescription(book.getDescription());
        bookFromDB.setAuthorName(book.getAuthorName());

        return bookRepository.save(bookFromDB);
    }

 @DeleteMapping("/books/{id}")
    public void delete( @PathVariable("id") int id) {

        bookRepository.delete(bookRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("book dose not exist")));
    }


}

