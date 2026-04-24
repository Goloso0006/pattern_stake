package com.pattern.stake.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class DiskControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void resetDisk() throws Exception {
        mockMvc = webAppContextSetup(context).build();
        mockMvc.perform(post("/disk/reset"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnCurrentStateAsJson() throws Exception {
        mockMvc.perform(get("/disk/state"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Idle"))
                .andExpect(jsonPath("$.message").value("Current state retrieved."))
                .andExpect(jsonPath("$.history").isArray());
    }

    @Test
    void shouldStartReadingAndReturnJsonResponse() throws Exception {
        mockMvc.perform(get("/disk/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Reading"))
                .andExpect(jsonPath("$.message").value("Read operation started."));
    }

    @Test
    void shouldWriteDataAndReturnJsonResponse() throws Exception {
        mockMvc.perform(post("/disk/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":\"hello disk\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Writing"))
                .andExpect(jsonPath("$.message").value("Data saved to disk."));
    }

    @Test
    void shouldResetDiskAfterInvalidTransition() throws Exception {
        mockMvc.perform(get("/disk/read"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":\"blocked\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/reset"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Idle"))
                .andExpect(jsonPath("$.message").value("Disk recovered from error to idle."));
    }

    @Test
    void shouldClearDiskContent() throws Exception {
        mockMvc.perform(post("/disk/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"data\":\"to-clear\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/reset"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/clear"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Disk content cleared."))
                .andExpect(jsonPath("$.content").value(""));
    }
}

