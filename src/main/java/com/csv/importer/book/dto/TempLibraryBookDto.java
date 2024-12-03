package com.csv.importer.book.dto;

import com.csv.importer.book.entity.TempLibraryBook;
import lombok.Getter;

@Getter
public class TempLibraryBookDto {
    private Long isbn;
    private String title;
    private Long libraryId;

    public TempLibraryBookDto(Long isbn, String title, Long libraryId) {
        this.isbn = isbn;
        this.title = title;
        this.libraryId = libraryId;
    }

    public static TempLibraryBookDto from(TempLibraryBook book){
        return new TempLibraryBookDto(
                book.getBookId(),
                book.getTitle(),
                book.getLibraryId()
        );
    }
}
