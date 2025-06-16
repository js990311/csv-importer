package com.csv.importer.domain.workspace.controller;

import com.csv.importer.domain.workspace.controller.request.CreateWorkspaceRequest;
import com.csv.importer.domain.workspace.dto.WorkspaceDto;
import com.csv.importer.domain.workspace.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WorkspaceController.class)
class WorkspaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private WorkspaceService workspaceService;

    @Test
    void postWorkspaceDto() throws Exception {
        // given
        Long id = 1L;
        String name = "name";

        // when
        when(workspaceService.createWorkspace(name)).thenReturn(new WorkspaceDto(id, name));

        // then
        mockMvc.perform(post("/workspace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new CreateWorkspaceRequest(name))))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", endsWith("/workspace/" + id)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andDo(print());
    }

    @Test
    void getWorkspaceDto() throws Exception {
        // given
        Long id = 1L;
        String name = "name";

        // when
        when(workspaceService.readById(id)).thenReturn(new WorkspaceDto(id, name));

        // then
        mockMvc.perform(get("/workspace/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andDo(print());

    }
}