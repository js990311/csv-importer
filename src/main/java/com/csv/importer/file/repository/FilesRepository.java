package com.csv.importer.file.repository;

import com.csv.importer.file.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<Files, Long> {
    Optional<Files> findByFileStoreName(String fileStoreName);

}
