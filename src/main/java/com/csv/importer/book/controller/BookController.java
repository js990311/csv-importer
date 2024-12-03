package com.csv.importer.book.controller;

import com.csv.importer.book.dto.TempLibraryBookDto;
import com.csv.importer.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public ListResponse findAll(){
        return new ListResponse(bookService.findAll());
    }

    @Getter
    public static class ListResponse{
        private Integer count;
        private List<TempLibraryBookDto> tbooks;
        public ListResponse(List<TempLibraryBookDto> books) {
            count = books.size();
            this.tbooks = books;
        }
    }
}
