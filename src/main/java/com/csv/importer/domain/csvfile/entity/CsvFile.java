package com.csv.importer.domain.csvfile.entity;

import com.csv.importer.domain.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    /* 관계 - Workspace */
    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    public void mapWorkspace(Workspace workspace){
        this.workspace = workspace;
        workspace.addCsvFiles(this);
    }

    /* 생성 */

    public CsvFile(String originalFileName, String storedFileName) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.uploadTime = LocalDateTime.now();
    }
}
