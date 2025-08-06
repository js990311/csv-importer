package com.csv.importer.domain.csvfile.controller;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CsvFileViewController {
    private final CsvFileService csvFileService;
    @GetMapping("/datas/{dataId}")
    public String getData(@PathVariable("dataId") long dataId, Model model){
        CsvFileDto data = csvFileService.readById(dataId);
        model.addAttribute("data", data);
        return "datas/id";
    }

    @GetMapping("/datas/{dataId}/preview")
    public String getDatafilePreview(@PathVariable("dataId") long dataId, Model model){
        String previewContent = csvFileService.previewDataFile(dataId);
        model.addAttribute("preview", previewContent);
        return "fragments::filePreview";
    }

    @PostMapping("/workspaces/{workspaceId}/datas")
    public String postCsvfile(@PathVariable("workspaceId") long workspaceId, @RequestParam("file") MultipartFile file){
        CsvFileDto csvFile = csvFileService.createCsvFile(workspaceId, file);
        return "redirect:/datas/"+csvFile.getId();
    }

    @GetMapping("/workspaces/{workspaceId}/datas")
    public String getDatas(@PathVariable("workspaceId") long workspaceId, Model model){
        List<CsvFileDto> datas = csvFileService.readByWorkspaceId(workspaceId);
        model.addAttribute("datas", datas);
        return "datas/fragments::dataListFragment";
    }

}
