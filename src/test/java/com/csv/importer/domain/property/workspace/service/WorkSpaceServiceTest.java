package com.csv.importer.domain.property.workspace.service;

import com.csv.importer.domain.property.controller.form.WorkColumnForm;
import com.csv.importer.domain.property.controller.form.WorkForm;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceDto;
import com.csv.importer.domain.property.controller.form.WorkSpaceForm;
import com.csv.importer.domain.property.workspace.dto.WorkSpaceWithWorksDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class WorkSpaceServiceTest {
    @Autowired private WorkSpaceService workSpaceService;
    private WorkSpaceForm sampleForm;
    @BeforeEach
    void setUp() {
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

        sampleForm = WorkSpaceForm.builder()
                .name("Form Workspace")
                .databaseUrl("jdbc:h2:mem:testdb")
                .username("username")
                .password("password")
                .driver("org.h2.Driver")
                .works(List.of(workForm))
                .build();
    }


        @Test
    void create() {

        WorkSpaceDto workSpaceDto = workSpaceService.create(sampleForm);
        assertNotNull(workSpaceDto);
    }


    @Test
    void testReadWorkspaceWithWorks() {
        // Given
        WorkSpaceDto created = workSpaceService.create(sampleForm);

        // When
        WorkSpaceWithWorksDto result = workSpaceService.readWorkspaceWithWorks(created.getId());

        // Then
        assertNotNull(result);
        assertEquals("Form Workspace", result.getWorkSpace().getName());
        assertEquals(1, result.getWorks().size());
        assertEquals("users", result.getWorks().getFirst().getWork().getTableName());
        assertEquals(2, result.getWorks().getFirst().getColumns().size());
    }

    @Test
    void testReadWorkspaceWithWorksThrowsException() {
        // Given
        Long invalidId = 9999L;

        // Expect
        assertThrows(NoSuchElementException.class, () -> {
            workSpaceService.readWorkspaceWithWorks(invalidId);
        });
    }

}