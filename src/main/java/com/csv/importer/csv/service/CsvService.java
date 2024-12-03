package com.csv.importer.csv.service;

import com.csv.importer.csv.dto.CsvExtractResult;
import com.csv.importer.csv.dto.CsvUploadDto;
import com.csv.importer.csv.extractor.CsvExtractorManager;
import com.csv.importer.csv.extractor.impl.UserCsvExtractor;
import com.csv.importer.csv.repository.CsvBatchInsertRepositoryManager;
import com.csv.importer.csv.writer.impl.UserCsvWriter;
import com.csv.importer.csv.file.dto.CsvFilesDto;
import com.csv.importer.csv.file.entity.Files;
import com.csv.importer.csv.file.service.FilesService;
import com.csv.importer.user.repository.UserBatchInsertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvService {
    private final CsvExtractorManager extractorManager;
    private final FilesService filesService;
    private final UserCsvWriter userCsvWriter;
    private final CsvBatchInsertRepositoryManager batchInsertRepositoryManager;

    @Transactional
    public CsvUploadDto extractCsv(String filename){
        CsvFilesDto csvFile = filesService.loadForCsv(filename);
        CsvExtractResult result = extractorManager.extract(csvFile.getFiles().getType() ,csvFile.getResource()).orElseThrow(RuntimeException::new);

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

        batchInsertRepositoryManager.batchInsert(csvFile.getFiles().getType(), result.getValidRecords());

        return CsvUploadDto.from(validFile, invalidFile, result);
    }


}
