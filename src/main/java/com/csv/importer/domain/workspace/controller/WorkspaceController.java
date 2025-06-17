package com.csv.importer.domain.workspace.controller;

import com.csv.importer.domain.workspace.controller.request.CreateWorkspaceRequest;
import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspace")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    /**
     * workspace 생성하기
     * @return
     */
    @PostMapping
    public ResponseEntity<WorkspaceDto> postWorkspaceDto(@RequestBody CreateWorkspaceRequest request){
        WorkspaceDto workspace = workspaceService.createWorkspace(request.getName());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(workspace.getId())
                .toUri();
        return ResponseEntity.created(location).body(workspace);
    }

    @GetMapping("/{id}")
    public WorkspaceDto getWorkspaceDto(@PathVariable(name = "id") Long id){
        return workspaceService.readById(id);
    }
}
