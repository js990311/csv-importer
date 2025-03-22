package com.csv.importer.domain.property.workspace.service;

import com.csv.importer.domain.property.workspace.WorkSpace;
import com.csv.importer.domain.property.workspace.dto.WorkColumnForm;
import com.csv.importer.domain.property.workspace.dto.WorkForm;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceForm;
import com.csv.importer.domain.property.workspace.repository.WorkSpaceRepository;
import com.csv.importer.domain.property.workspace.work.Work;
import com.csv.importer.domain.property.workspace.work.columns.WorkColumn;
import com.csv.importer.domain.property.workspace.work.columns.repository.WorkColumnRepository;
import com.csv.importer.domain.property.workspace.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorkSpaceService {
    private final WorkSpaceRepository workSpaceRepository;
    private final WorkRepository workRepository;
    private final WorkColumnRepository workColumnRepository;

    /* Create */
    @Transactional
    public WorkSpaceDto create(WorkSpaceForm form){
        WorkSpace workSpace = WorkSpace.builder()
                .name(form.getName())
                .databaseUrl(form.getDatabaseUrl())
                .driver(form.getDriver())
                .username(form.getUsername())
                .password(form.getPassword())
                .build();
        workSpace = workSpaceRepository.save(workSpace);

        List<Work> works = new ArrayList<>();
        if(form.getWorks() != null){
            for(WorkForm workForm : form.getWorks()){
                Work work = Work.builder()
                        .tableName(workForm.getTableName())
                        .workSpace(workSpace)
                        .build();
                workSpace.addWork(work);
                work = workRepository.save(work);

                if(workForm.getColumns() != null){
                    for(WorkColumnForm columnForm : workForm.getColumns()){
                        WorkColumn column = WorkColumn.builder()
                                .name(columnForm.getName())
                                .type(columnForm.getType())
                                .csvIndex(columnForm.getCsvIndex())
                                .validationType(columnForm.getValidationType())
                                .nullable(columnForm.getNullable())
                                .regex(columnForm.getRegex())
                                .min(columnForm.getMin())
                                .max(columnForm.getMax())
                                .work(work)
                                .build();

                        work.addWorkColumn(column);
                        column = workColumnRepository.save(column);
                    }
                }
            }
        }
        return WorkSpaceDto.of(workSpace);
    }
}
