package com.csv.importer.domain.work.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "work_files")
public class WorkFile {
    @Id
    @GeneratedValue
    @Column(name = "work_file_id")
    private Long id;

    private String name;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @Column
    private LocalDateTime uploadTime;

    public WorkFile(String originalFileName, String storedFileName) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        uploadTime = LocalDateTime.now();
    }
}
