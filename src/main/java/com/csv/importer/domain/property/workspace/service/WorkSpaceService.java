package com.csv.importer.domain.property.workspace.service;

import com.csv.importer.domain.property.workspace.WorkSpace;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceForm;
import com.csv.importer.domain.property.workspace.repository.WorkSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorkSpaceService {
    private final WorkSpaceRepository workSpaceRepository;

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
        return WorkSpaceDto.of(workSpace);
    }
}
