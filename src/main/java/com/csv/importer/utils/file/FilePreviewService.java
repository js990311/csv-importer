package com.csv.importer.utils.file;

import com.rejs.csvloader.file.FileSystemAccessObject;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class FilePreviewService {
    private int PREVIEW_LIMIT = 100;
    private final FileSystemAccessObject fileSystemAccessObject;

    public String previewFile(String filename){
        Resource resource = fileSystemAccessObject.load(filename);
        return extracted(resource);
    }

    private String extracted(Resource resource) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))){
            String line;
            int cnt = 0;
            while ((line=reader.readLine())!= null && cnt < PREVIEW_LIMIT){
                sb.append(line);
                cnt++;
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
