package com.csv.importer.domain.property.workspace.dto;

import com.csv.importer.domain.property.workspace.WorkSpace;
import com.csv.importer.domain.property.workspace.work.Work;
import com.csv.importer.domain.property.workspace.work.dto.WorkWithColumnsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class WorkSpaceWithWorksDto {
    private WorkSpaceDto workSpace;
    private List<WorkWithColumnsDto> works;

    public WorkSpaceWithWorksDto(WorkSpaceDto workSpace, List<WorkWithColumnsDto> works) {
        this.workSpace = workSpace;
        this.works = works;
    }

    public static WorkSpaceWithWorksDto of(WorkSpace workSpace, List<Work> works){
        return new WorkSpaceWithWorksDto(WorkSpaceDto.of(workSpace), works.stream().map(WorkWithColumnsDto::of).toList());
    }
}
