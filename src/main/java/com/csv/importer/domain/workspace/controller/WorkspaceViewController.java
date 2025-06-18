package com.csv.importer.domain.workspace.controller;

import com.csv.importer.domain.workspace.controller.request.CreateWorkspaceRequest;
import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workspaces")
public class WorkspaceViewController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public String getWorkspaceHome(@RequestParam(value = "p", defaultValue = "1") int p, @RequestParam(value = "s", defaultValue = "20") int s, Model model){
        Page<WorkspaceDto> workspaces = workspaceService.readAll(p - 1, s);
        model.addAttribute("workspaces", workspaces);
        return "workspaces/home";
    }

    @GetMapping("/{workspaceId}")
    public String getWorkspaceId(@PathVariable(name= "workspaceId") long workspaceId, Model model){
        WorkspaceDto workspace = workspaceService.readById(workspaceId);
        model.addAttribute("workspace", workspace);
        return "/workspaces/id";
    }

    @GetMapping("/create")
    public String getCreateWorkspace(Model model){
        model.addAttribute("form", new CreateWorkspaceRequest());
        return "workspaces/create";
    }

    @PostMapping("/create")
    public String postCreteWorkspace(@ModelAttribute CreateWorkspaceRequest request){
        WorkspaceDto workspace = workspaceService.createWorkspace(request.getName());
        return "redirect:/workspaces/" + workspace.getId();
    }
}
