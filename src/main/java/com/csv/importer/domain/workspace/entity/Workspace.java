package com.csv.importer.domain.workspace.entity;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import com.csv.importer.domain.workfile.entity.WorkFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue
    @Column(name = "workspace_id")
    private Long id;

    @Column
    private String name;

    /* 관계 : workFiles */

    @OneToMany(mappedBy = "workspace")
    private List<WorkFile> workFiles = new ArrayList<>();

    /**
     * workFile 내부에서 호출하는 것을 가정하고 작성한 메서드이므로 외부에서 호출하지 말 것
     * @param workFile
     */
    public void addWorkFiles(WorkFile workFile){
        workFiles.add(workFile);
    }

    /* 관계 : CsvFiles */
    @OneToMany(mappedBy = "workspace")
    private List<CsvFile> csvFiles = new ArrayList<>();

    /**
     * csvFile 내부에서 호출하는 것을 가정하고 작성한 메서드이므로 외부에서 호출하지 말 것
     * @param csvFile
     */
    public void addCsvFiles(CsvFile csvFile){
        csvFiles.add(csvFile);
    }

    /* 생성 */

    public Workspace(String name) {
        this.name = name;
    }
}
