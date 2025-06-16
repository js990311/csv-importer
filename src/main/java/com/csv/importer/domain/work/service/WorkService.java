package com.csv.importer.domain.work.service;

import com.rejs.csvloader.CsvLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkService {
    private final CsvLoadService csvLoadService;
}
