package com.csv.importer.domain.property.workspace.service;

import com.csv.importer.TestcontainersConfiguration;
import com.csv.importer.domain.property.workspace.dto.WorkColumnForm;
import com.csv.importer.domain.property.workspace.dto.WorkForm;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@SpringBootTest
class WorkSpaceServiceTest {
    @Autowired private WorkSpaceService workSpaceService;

    @Test
    void create() {
        WorkColumnForm usernameColumnForm = WorkColumnForm.builder()
                .name("username")
                .type("String")
                .csvIndex(0)
                .nullable(false)
                .build();

        WorkColumnForm ageColumnForm = WorkColumnForm.builder()
                .name("age")
                .type("Integer")
                .csvIndex(1)
                .min(1L)
                .build();

        WorkForm workForm = WorkForm.builder()
                .tableName("users")
                .columns(List.of(usernameColumnForm, ageColumnForm))
                .build();

        WorkSpaceForm workSpaceForm = WorkSpaceForm.builder()
                .name("Form Workspace")
                .databaseUrl("jdbc:h2:mem:testdb")
                .username("username")
                .password("password")
                .driver("org.h2.Driver")
                .works(List.of(workForm))
                .build();

        WorkSpaceDto workSpaceDto = workSpaceService.create(workSpaceForm);
        assertNotNull(workSpaceDto);
    }
}