package com.csv.importer.file.impl;

import com.csv.importer.file.FileSystemAccessObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class LocalFileSystemAccessObject implements FileSystemAccessObject {

    private static String ROOT = "file/";

    @Override
    public void save(String path, MultipartFile file) {
        Path localSavePath = Paths.get(ROOT + path);
        try {
            file.transferTo(localSavePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Resource load(String path) {
        Path localSavePath = Paths.get(ROOT + path);
        if(!Files.exists(localSavePath)){
            throw new NoSuchElementException();
        }
        try {
            return new UrlResource(localSavePath.toUri());
        }catch (MalformedURLException e){
            throw new RuntimeException(e);
        }
    }
}
