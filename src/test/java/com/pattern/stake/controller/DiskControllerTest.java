package com.pattern.stake.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
                .andExpect(jsonPath("$.message").value("Current state retrieved."));
    }

    @Test
    void shouldStartReadingAndReturnJsonResponse() throws Exception {
        mockMvc.perform(post("/disk/read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Reading"))
                .andExpect(jsonPath("$.message").value("Disk started reading."));
    }

    @Test
    void shouldStartWritingAndReturnJsonResponse() throws Exception {
        mockMvc.perform(post("/disk/write"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Writing"))
                .andExpect(jsonPath("$.message").value("Disk started writing."));
    }

    @Test
    void shouldResetDiskAfterInvalidTransition() throws Exception {
        mockMvc.perform(post("/disk/read"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/write"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/disk/reset"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("Idle"))
                .andExpect(jsonPath("$.message").value("Disk recovered from error to idle."));
    }
}

