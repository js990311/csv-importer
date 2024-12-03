package com.csv.importer.book.service;

import com.csv.importer.book.dto.TempLibraryBookDto;
import com.csv.importer.book.repository.TempLibraryBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final TempLibraryBookRepository bookRepository;

    public List<TempLibraryBookDto> findAll(){
        return bookRepository.findAll().stream().map(TempLibraryBookDto::from).collect(Collectors.toList());
    }
}
