package com.myform.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myform.test.dto.FormDtoRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final int id = 1;
    private static final String URL = "/api/v1";

    @Test
    public void create() throws Exception {
        FormDtoRequest request = new FormDtoRequest()
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectorsId(Collections.singleton(1));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post(URL + "/form")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        ResultActions perform = mockMvc.perform(requestBuilder);

        perform.andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        FormDtoRequest request = new FormDtoRequest()
                .setUsername("TestUsername")
                .setAgreement(true)
                .setSectorsId(Collections.singleton(1));

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .put(URL + "/form/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request));

        ResultActions perform = mockMvc.perform(requestBuilder);

        perform.andExpect(status().isOk());
    }

    @Test
    void getFormByIdSuccess() throws Exception {

        mockMvc.perform(get(URL + "/form/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id));
    }

    @Test
    public void getFormByIdFailed() throws Exception {
        int id = 1001;
        mockMvc.perform(get(URL + "/form/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}