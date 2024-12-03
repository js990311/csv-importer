package com.csv.importer.book.repository;

import com.csv.importer.book.entity.TempLibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempLibraryBookRepository extends JpaRepository<TempLibraryBook, Long> {
}
