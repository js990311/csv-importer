package com.csv.importer.domain.workfile.controller;

import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
import com.csv.importer.domain.workspace.controller.request.CreateWorkspaceRequest;
import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkFileController.class)
class WorkFileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private WorkFileService workFileService;

    @Test
    void getWorks() throws Exception {
        Long workspaceId = 1L;
        Long workFileId = 1L;
        String originalFileName = "originalFileName";
        String storedFileName = "storedFileName";
        LocalDateTime uploadTime = LocalDateTime.now();

        when(workFileService.findWorkByWorkspaceId(workFileId)).thenReturn(List.of(
                new WorkFileDto(workFileId, originalFileName, storedFileName, uploadTime, 1L)
        ));

        mockMvc.perform(get("/api/workspaces/{id}/works", workspaceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(workFileId))
                .andExpect(jsonPath("$[0].originalFileName").value(originalFileName))
                .andExpect(jsonPath("$[0].storedFileName").value(storedFileName))
                .andDo(print())
        ;
    }

    @Test
    void postWorkfile() throws Exception {
        long workspaceId = 1L;
        Long workFileId = 1L;
        String originalFileName = "originalFileName";
        String storedFileName = "storedFileName";
        LocalDateTime uploadTime = LocalDateTime.now();
        String fileName = "new_file.txt";

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                fileName,
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes(StandardCharsets.UTF_8)
        );

        when(workFileService.createWorkFile(workspaceId, mockFile))
                .thenReturn(
                        new WorkFileDto(workFileId, originalFileName, storedFileName, uploadTime, 1L)
                );

        mockMvc.perform(multipart("/api/workspaces/{workspaceId}/works", workspaceId)
                .file(mockFile))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", endsWith("/works/" + workFileId)))
                .andExpect(jsonPath("$.id").value(workFileId))
                .andExpect(jsonPath("$.originalFileName").value(originalFileName))
                .andExpect(jsonPath("$.storedFileName").value(storedFileName))
                .andDo(print());

    }

    @Test
    void getWorkfile() throws Exception {
        Long workspaceId = 1L;
        Long workFileId = 1L;
        String originalFileName = "originalFileName";
        String storedFileName = "storedFileName";
        LocalDateTime uploadTime = LocalDateTime.now();

        when(workFileService.findById(workFileId)).thenReturn(
                new WorkFileDto(workFileId, originalFileName, storedFileName, uploadTime, 1L)
        );

        mockMvc.perform(get("/api/works/{id}", workFileId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(workFileId))
                .andExpect(jsonPath("$.originalFileName").value(originalFileName))
                .andExpect(jsonPath("$.storedFileName").value(storedFileName))
                .andDo(print())
        ;

    }
}