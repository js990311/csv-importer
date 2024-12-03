package com.csv.importer.book.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class TempLibraryBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long bookId;
    @Column(columnDefinition = "VARCHAR(255)")
    private String title;
    @Column
    private Long libraryId;
}
