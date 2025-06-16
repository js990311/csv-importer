package com.csv.importer.utils.file;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public class FileExtensionUtils {
    private final static Set<String> YAML_EXTENSIONS = Set.of("yaml", "yml");
    private final static Set<String> CSV_EXTENSION = Set.of("csv");

    public static boolean isCsvFile(MultipartFile file){
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        return CSV_EXTENSION.contains(extension.toLowerCase());
    }

    public static boolean isYamlFile(MultipartFile file){
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        return YAML_EXTENSIONS.contains(extension.toLowerCase());
    }
}
