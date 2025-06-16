package com.csv.importer.utils.file;

import com.rejs.csvloader.file.FileSystemAccessObject;
import org.springframework.web.multipart.MultipartFile;

public interface MultipartFileSystemAccessObject extends FileSystemAccessObject {
    public void save(String path, MultipartFile file);
}
