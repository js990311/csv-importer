package com.csv.importer.domain.csvfile.controller;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/datas")
public class CsvFileViewController {
    private final CsvFileService csvFileService;
    @GetMapping("/{dataId}")
    public String getData(@PathVariable("dataId") long dataId, Model model){
        CsvFileDto data = csvFileService.readById(dataId);
        model.addAttribute("data", data);
        return "datas/id";
    }

}
