package com.csv.importer.domain.csvfile.service;

import com.csv.importer.domain.csvfile.entity.CsvFile;
import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.repository.CsvFileRepository;
import com.csv.importer.utils.file.MultipartFileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CsvFileService {
    private final CsvFileRepository csvFileRepository;
    private final MultipartFileSystemAccessObject fileSAO;

    /* Create */
    public CsvFileDto saveFile(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + ".csv";
        CsvFile csvFile = new CsvFile(originalFilename, storedFileName);
        fileSAO.save(storedFileName, file);
        csvFile = csvFileRepository.save(csvFile);
        return CsvFileDto.of(csvFile);
    }
}
