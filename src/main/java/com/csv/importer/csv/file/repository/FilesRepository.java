package com.csv.importer.csv.file.repository;

import com.csv.importer.csv.file.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<Files, Long> {
    Optional<Files> findByFileStoreName(String fileStoreName);

}
