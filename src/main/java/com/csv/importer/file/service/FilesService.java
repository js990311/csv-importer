package com.csv.importer.file.service;

import com.csv.importer.file.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FilesService {
    private final FilesRepository filesRepository;


}
