package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.service.QuantityMeasurementService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuantityMeasurementService service;

    @Test
    void testAddEndpoint() throws Exception {

        String json = """
        {
          "thisQuantityDTO": {
            "value": 1,
            "unit": "METER",
            "measurementType": "LengthUnit"
          },
          "thatQuantityDTO": {
            "value": 100,
            "unit": "CENTIMETER",
            "measurementType": "LengthUnit"
          }
        }
        """;

        mockMvc.perform(post("/api/v1/quantities/add")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }
}