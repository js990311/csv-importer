package com.csv.importer.domain.property.workspace;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
