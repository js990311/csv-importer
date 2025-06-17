package com.csv.importer.domain.workspace.controller;

import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
