package com.telzir.falemais.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CallCostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateCost() throws Exception {
        MockHttpServletRequestBuilder request = get("/v1/phone-call/cost")
                .queryParam("planId", "1")
                .queryParam("sourceDDD", "011")
                .queryParam("destinationDDD", "016")
                .queryParam("time", "20")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.costWithPlan", is(0)))
                .andExpect(jsonPath("$.costWithoutPlan", is(38.0)));
    }

    @Test
    void testCalculateCost_planNotFound() throws Exception {
        MockHttpServletRequestBuilder request = get("/v1/phone-call/cost")
                .queryParam("planId", "100")
                .queryParam("sourceDDD", "011")
                .queryParam("destinationDDD", "016")
                .queryParam("time", "20")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(status().reason(is("Plano não encontrado")));
    }

    @Test
    void testCalculateCost_costNotFound() throws Exception {
        MockHttpServletRequestBuilder request = get("/v1/phone-call/cost")
                .queryParam("planId", "1")
                .queryParam("sourceDDD", "031")
                .queryParam("destinationDDD", "061")
                .queryParam("time", "20")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(status().reason(is("Tarifa entre os DDDs não encontrada")));
    }

    @Test
    void testListPlans() throws Exception {
        MockHttpServletRequestBuilder request = get("/v1/phone-plans")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, 3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("FaleMais 30", "FaleMais 60", "FaleMais 120")))
                .andExpect(jsonPath("$[*].minutes", containsInAnyOrder(30.0, 60.0, 120.0)));
    }

}
