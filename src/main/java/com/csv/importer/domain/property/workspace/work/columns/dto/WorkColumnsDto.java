package com.csv.importer.domain.property.workspace.work.columns.dto;

import com.csv.importer.domain.property.workspace.work.columns.WorkColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkColumnsDto {
    private Long id;
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
    private Boolean nullable;
    private String regex;
    private Long min;
    private Long max;


    public static WorkColumnsDto of(WorkColumn column){
        return WorkColumnsDto.builder()
                .id(column.getId())
                .name(column.getName())
                .type(column.getType())
                .csvIndex(column.getCsvIndex())
                .validationType(column.getValidationType())
                .nullable(column.getNullable())
                .regex(column.getRegex())
                .min(column.getMin())
                .max(column.getMax())
                .build();
    }
}
