package com.csv.importer.domain.property.workspace;

import com.csv.importer.domain.property.work.Work;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspaces")
public class WorkSpace {
    @Id
    @GeneratedValue
    @Column(name = "workspace_id")
    private Long id;

    @Column
    private String name;

    /* workspace의 데이터베이스 관련 */

    @Column
    private String databaseUrl;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String driver;


    /* 관계 - Work */
    @OneToMany(mappedBy = "workSpace")
    private List<Work> works;

}
