package com.csv.importer.yaml.service;

import com.csv.importer.csv.file.access.FileSystemAccessObject;
import com.csv.importer.yaml.dto.Root;
import com.csv.importer.yaml.dto.Works;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class YamlLoader {
    private final FileSystemAccessObject fileSAO;

    public Root loadYaml(String path){
        Resource yamlFiles = fileSAO.load(path);
        Yaml yaml = new Yaml(new Constructor(Root.class, new LoaderOptions()));

        try(InputStream inputStream = yamlFiles.getInputStream()){
            Root works = yaml.load(inputStream);
            return works;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
