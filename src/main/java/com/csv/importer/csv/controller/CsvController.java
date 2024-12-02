package com.csv.importer.csv.controller;

import com.csv.importer.csv.dto.CsvUploadDto;
import com.csv.importer.csv.service.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/csv")
@RestController
public class CsvController {
    private final CsvService csvService;

    @GetMapping("/{fileStoreName}")
    public CsvUploadDto extractCsv(@PathVariable("fileStoreName") String filename){
        CsvUploadDto csvUploadDto = csvService.extractCsv(filename);
        return csvUploadDto;
    }

}
