package com.csv.importer.domain.property.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkColumnForm {
    /* ColumnProperty 관련 속성 */
    private String name;
    private String type;
    private Integer csvIndex;
    private String validationType;
    private Boolean nullable;

    /* ConstraintsProperty 관련 속성  */
    private String regex;
    private Long min;
    private Long max;
}
