package com.csv.importer.domain.work.file.entity;

import com.csv.importer.domain.work.space.entity.Workspace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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


    /* 관계 : Workspace */

    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    public void mapWorkspace(Workspace workspace){
        this.workspace = workspace;
        workspace.addWorkFiles(this);
    }

    /* 생성 */

    public WorkFile(String originalFileName, String storedFileName) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        uploadTime = LocalDateTime.now();
    }
}
