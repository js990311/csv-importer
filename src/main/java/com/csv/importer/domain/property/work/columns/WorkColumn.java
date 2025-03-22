package com.csv.importer.domain.property.work.columns;

import com.csv.importer.domain.property.work.Work;
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
@Table(name = "work_columns")
public class WorkColumn {
    @Id
    @GeneratedValue
    @Column(name = "work_column_id")
    private Long id;

    /* ColumnProperty 관련 속성 */
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
    private Boolean nullable;

    /* ConstraintsProperty 관련 속성  */
    private String regex;
    private Long min;
    private Long max;

    /* 관계 - 관계 */
    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    @Column(name = "work_id", insertable = false, updatable = false)
    private Long workId;

}
