package com.csv.importer.domain.property.work;

import com.csv.importer.domain.property.work.columns.WorkColumn;
import com.csv.importer.domain.property.workspace.WorkSpace;
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
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue
    @Column(name = "works_id")
    private Long id;

    @Column
    private String tableName;

    /* 관계 : workspace */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private WorkSpace workSpace;

    @Column(name = "workspace_id", updatable = false, insertable = false)
    private Long workSpaceId;

    /* 관계 : WorkColumn */

    @OneToMany(mappedBy = "work")
    private List<WorkColumn> columns;
}
