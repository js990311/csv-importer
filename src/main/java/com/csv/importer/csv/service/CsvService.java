package com.csv.importer.csv.service;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.dto.CsvUploadDto;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.csv.writer.impl.UserCsvWriter;
import com.csv.importer.file.dto.CsvFilesDto;
import com.csv.importer.file.dto.ResourceDto;
import com.csv.importer.file.entity.Files;
import com.csv.importer.file.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvService {
    private final UserCsvExtractor userCsvExtractor;
    private final FilesService filesService;
    private final UserCsvWriter userCsvWriter;

    @Transactional
    public CsvUploadDto extractCsv(String filename){
        CsvFilesDto csvFile = filesService.loadForCsv(filename);
        CsvExtractResult result = userCsvExtractor.extract(csvFile.getResource());

        String originalFileName = csvFile.getFiles().getOriginalFileName();
        int dotIdx = originalFileName.lastIndexOf('.');
        String shortFilename = originalFileName.substring(0, dotIdx);
        String fileExtension = originalFileName.substring(dotIdx+1);

        Files validFile = new Files(csvFile.getFiles().getType(), shortFilename + "-valid." + fileExtension);
        Files invalidFile = new Files(csvFile.getFiles().getType(),shortFilename + "-invalid." + fileExtension);
        Resource validCsv = userCsvWriter.writeCsv(result.getValidRecords());
        Resource inValidCsv = userCsvWriter.writeCsv(result.getInValidRecords());

        filesService.saveCsvFile(validFile, validCsv);
        filesService.saveCsvFile(invalidFile, inValidCsv);

        return CsvUploadDto.from(validFile, invalidFile, result);
    }


}
