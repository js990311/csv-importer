package com.csv.importer.domain.property.workspace.work.dto;

import com.csv.importer.domain.property.workspace.work.Work;
import com.csv.importer.domain.property.workspace.work.columns.dto.WorkColumnsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class WorkWithColumnsDto {
    private WorkDto work;
    private List<WorkColumnsDto> columnsDtos;

    public WorkWithColumnsDto(WorkDto work, List<WorkColumnsDto> columnsDtos) {
        this.work = work;
        this.columnsDtos = columnsDtos;
    }

    /**
     *
     * @param work Fetch join되지 않은 work는 사용하지 말 것
     * @return
     */
    public static WorkWithColumnsDto of(Work work){
        return new WorkWithColumnsDto(WorkDto.of(work), work.getColumns().stream().map(WorkColumnsDto::of).toList());
    }
}
