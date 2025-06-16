package com.csv.importer.domain.work.controller;

import com.csv.importer.domain.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/works")
public class WorkController {
    private final WorkService workService;

    @GetMapping
    public ResponseEntity<Void> getWork(@RequestParam("workId") Long workId, @RequestParam("dataId") Long dataId){
        workService.loadCsv(workId, dataId);
        return ResponseEntity.ok().build();
    }
}
