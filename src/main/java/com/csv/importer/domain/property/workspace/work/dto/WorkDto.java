package com.csv.importer.domain.property.workspace.work.dto;

import com.csv.importer.domain.property.workspace.work.Work;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Getter
public class WorkDto {
    private Long id;
    private String tableName;
    private Long workSpaceId;

    public WorkDto(Long id, String tableName, Long workSpaceId) {
        this.id = id;
        this.tableName = tableName;
        this.workSpaceId = workSpaceId;
    }

    public static WorkDto of(Work work){
        return WorkDto.builder()
                .id(work.getId())
                .tableName(work.getTableName())
                .workSpaceId(work.getWorkSpaceId())
                .build();
    }
}
