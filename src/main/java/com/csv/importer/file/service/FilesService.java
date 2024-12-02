package com.csv.importer.file.service;

import com.csv.importer.file.access.FileSystemAccessObject;
import com.csv.importer.file.dto.FilesDto;
import com.csv.importer.file.dto.ResourceDto;
import com.csv.importer.file.entity.Files;
import com.csv.importer.file.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FilesService {
    private final FilesRepository filesRepository;
    private final FileSystemAccessObject fileSystemAO;

    @Transactional
    public FilesDto saveFile(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        Files files = new Files(originalFilename);
        fileSystemAO.save(files.getStorePath(), file);
        filesRepository.save(files);
        return FilesDto.from(files);
    }

    public ResourceDto loadByFilename(String filename){
        Optional<Files> opt = filesRepository.findByFileStoreName(filename);
        if(opt.isEmpty()){
            throw new NoSuchElementException();
        }
        Files files = opt.get();
        Resource resource = fileSystemAO.load(files.getStorePath());
        return new ResourceDto(resource, files.getOriginalFileName());
    }
}
