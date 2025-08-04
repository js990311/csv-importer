package com.csv.importer.domain.workfile.controller;

import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class WorkFileViewController {
    private final WorkFileService workFileService;

    @GetMapping("/works/{workId}")
    public String getWorkfile(@PathVariable("workId") long workId, Model model){
        WorkFileDto work = workFileService.findById(workId);
        model.addAttribute("work", work);
        return "works/id";
    }

    @GetMapping("/workspaces/{workspaceId}/works")
    public String getWorks(@PathVariable("workspaceId") long workspaceId, Model model){
        List<WorkFileDto> works = workFileService.findWorkByWorkspaceId(workspaceId);
        model.addAttribute("works", works);
        return "works/fragments::workListFragment";
    }

    @PostMapping("/workspaces/{workspaceId}/works")
    public String postWorkfile(@PathVariable("workspaceId") long workspaceId, @RequestParam("file") MultipartFile file){
        WorkFileDto workFile = workFileService.createWorkFile(workspaceId, file);
        return "redirect:/works/"+workFile.getId();
    }

}
