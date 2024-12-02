package com.csv.importer.file.repository;

import com.csv.importer.file.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {
}
