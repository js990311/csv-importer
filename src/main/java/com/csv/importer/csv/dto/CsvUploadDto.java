package com.csv.importer.csv.dto;

import com.csv.importer.file.dto.FilesDto;
import com.csv.importer.file.entity.Files;
import lombok.Getter;

@Getter
public class CsvUploadDto {
    private FilesDto inValidDataFiles;
    private Integer validDatas;
    private Integer inValidDatas;

    public CsvUploadDto(FilesDto inValidDataFiles, Integer validDatas, Integer inValidDatas) {
        this.inValidDataFiles = inValidDataFiles;
        this.validDatas = validDatas;
        this.inValidDatas = inValidDatas;
    }

    public static CsvUploadDto from(Files files, CsvExtractResult extractResult){
        return new CsvUploadDto(
                FilesDto.from(files),
                extractResult.getValidRecords().size(),
                extractResult.getInValidRecords().size()
        );
    }
}
