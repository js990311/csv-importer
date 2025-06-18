package com.csv.importer.domain.workfile.controller;

import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/works")
public class WorkFileViewController {
    private final WorkFileService workFileService;

    @GetMapping("/{workId}")
    public String getWorkfile(@PathVariable("workId") long workId, Model model){
        WorkFileDto work = workFileService.findById(workId);
        model.addAttribute("work", work);
        return "works/id";
    }

}
