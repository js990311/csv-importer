package com.csv.importer.domain.property.workspace.controller;

import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceForm;
import com.csv.importer.domain.property.workspace.repository.WorkSpaceRepository;
import com.csv.importer.domain.property.workspace.service.WorkSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspace")
public class WorkSpaceController {
    private final WorkSpaceService workSpaceService;

    @PostMapping
    public WorkSpaceDto createWorkSpace(@RequestBody WorkSpaceForm form){
        return workSpaceService.create(form);
    }
}
