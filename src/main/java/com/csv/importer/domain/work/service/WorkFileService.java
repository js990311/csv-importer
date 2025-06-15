package com.csv.importer.domain.work.service;

import com.csv.importer.domain.file.CsvFile;
import com.csv.importer.domain.file.dto.CsvFileDto;
import com.csv.importer.domain.file.service.util.MultipartFileSystemAccessObject;
import com.csv.importer.domain.work.dto.WorkFileDto;
import com.csv.importer.domain.work.entity.WorkFile;
import com.csv.importer.domain.work.repository.WorkFileRepository;
import com.rejs.csvloader.file.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WorkFileService {
    private final WorkFileRepository workFileRepository;

    @Qualifier("multipartFileLocalSystemAccessObject")
    private final FileSystemAccessObject fileSAO;

    // CREATE
    @Transactional
    public WorkFileDto createWorkFile(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString() + ".csv";
        WorkFile workFile = new WorkFile(originalFilename, storedFileName);
        fileSAO.save(storedFileName, file.getResource());
        workFile = workFileRepository.save(workFile);
        return WorkFileDto.of(workFile);
    }

    // READ
    public WorkFileDto findById(Long id){
        WorkFile workFile = workFileRepository.findById(id).orElseThrow();
        return WorkFileDto.of(workFile);
    }

    public Page<WorkFileDto> findAll(int p, int s){
        return workFileRepository.findAllBy(PageRequest.of(p,s)).map(WorkFileDto::of);
    }

    // UPDATE

    // DELETE
    @Transactional
    public void deleteById(Long id){
        WorkFile workFile = workFileRepository.findById(id).orElseThrow();
        workFileRepository.delete(workFile);
    }
}
