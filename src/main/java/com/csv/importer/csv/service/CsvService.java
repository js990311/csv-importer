package com.csv.importer.csv.service;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.dto.CsvUploadDto;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.file.dto.CsvFilesDto;
import com.csv.importer.file.dto.ResourceDto;
import com.csv.importer.file.entity.Files;
import com.csv.importer.file.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvService {
    private final UserCsvExtractor userCsvExtractor;
    private final FilesService filesService;

    @Transactional
    public CsvUploadDto extractCsv(String filename){
        CsvFilesDto csvFile = filesService.loadForCsv(filename);
        CsvExtractResult result = userCsvExtractor.extract(csvFile.getResource());
        String inValidFiles = createInValidFiles(csvFile.getFiles().getOriginalFileName());

        // TODO 실패한 데이터모음은 다음에...
        Files invalidFile = new Files(csvFile.getFiles().getType(),inValidFiles);

        return CsvUploadDto.from(invalidFile, result);
    }

    private String createInValidFiles(String filename){
        int dotIdx = filename.lastIndexOf('.');
        return filename.substring(0, dotIdx) + "-invalid." + filename.substring(dotIdx+1);
    }

}
