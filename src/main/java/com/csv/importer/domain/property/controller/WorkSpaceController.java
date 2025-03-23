package com.csv.importer.domain.property.controller;

import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.controller.form.WorkSpaceForm;
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
