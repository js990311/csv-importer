package com.csv.importer.domain.workspace.entity;

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

    public void addWorkFiles(WorkFile workFile){
        workFiles.add(workFile);
    }

    public Workspace(String name) {
        this.name = name;
    }
}
