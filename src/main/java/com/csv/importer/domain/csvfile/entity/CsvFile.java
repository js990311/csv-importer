package com.csv.importer.domain.csvfile.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "csv_files")
public class CsvFile {
    @Id
    @GeneratedValue
    @Column(name = "csv_files_id")
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @Column
    private LocalDateTime uploadTime;

    public CsvFile(String originalFileName, String storedFileName) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = LocalDateTime.now();
    }
}
