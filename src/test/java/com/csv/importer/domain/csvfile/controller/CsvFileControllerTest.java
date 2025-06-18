package com.csv.importer.domain.csvfile.controller;

import com.csv.importer.domain.csvfile.dto.CsvFileDto;
import com.csv.importer.domain.csvfile.service.CsvFileService;
import com.csv.importer.domain.workfile.dto.WorkFileDto;
import com.csv.importer.domain.workfile.service.WorkFileService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(CsvFileController.class)
class CsvFileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private CsvFileService csvFileService;


    @Test
    void getDatas() throws Exception {
        Long workspaceId = 1L;
        Long dataId = 1L;
        String originalFileName = "originalFileName";
        String storedFileName = "storedFileName";
        LocalDateTime uploadTime = LocalDateTime.now();

        when(csvFileService.readByWorkspaceId(workspaceId)).thenReturn(List.of(
                new CsvFileDto(dataId, originalFileName, storedFileName, uploadTime)
        ));

        mockMvc.perform(get("/api/workspaces/{id}/datas", workspaceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(dataId))
                .andExpect(jsonPath("$[0].originalFileName").value(originalFileName))
                .andExpect(jsonPath("$[0].storedFileName").value(storedFileName))
                .andDo(print())
        ;

    }

    @Test
    void postData() throws Exception {
        Long workspaceId = 1L;
        Long dataId = 1L;
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

        when(csvFileService.createCsvFile(workspaceId, mockFile))
                .thenReturn(
                        new CsvFileDto(dataId, originalFileName, storedFileName, uploadTime)
                );
        mockMvc.perform(multipart("/api/workspaces/{workspaceId}/datas", workspaceId)
                        .file(mockFile))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", endsWith("/datas/" + dataId)))
                .andExpect(jsonPath("$.id").value(dataId))
                .andExpect(jsonPath("$.originalFileName").value(originalFileName))
                .andExpect(jsonPath("$.storedFileName").value(storedFileName))
                .andDo(print());

    }

    @Test
    void getData() throws Exception {
        Long dataId = 1L;
        String originalFileName = "originalFileName";
        String storedFileName = "storedFileName";
        LocalDateTime uploadTime = LocalDateTime.now();

        when(csvFileService.readById(dataId)).thenReturn(
                new CsvFileDto(dataId, originalFileName, storedFileName, uploadTime)
        );

        mockMvc.perform(get("/api/datas/{id}", dataId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dataId))
                .andExpect(jsonPath("$.originalFileName").value(originalFileName))
                .andExpect(jsonPath("$.storedFileName").value(storedFileName))
                .andDo(print())
        ;

    }
}