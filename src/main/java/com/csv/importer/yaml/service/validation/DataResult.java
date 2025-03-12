package com.csv.importer.yaml.service.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataResult {
    private List<Object[]> validRecords;
    private List<String[]> inValidRecords;

    public DataResult() {
        this.validRecords = new ArrayList<>();
        this.inValidRecords = new ArrayList<>();
    }

    public void addValidRecords(Object[] record){
        validRecords.add(record);
    }

    public void addInValidRecords(String[] record){
        inValidRecords.add(record);
    }

}
