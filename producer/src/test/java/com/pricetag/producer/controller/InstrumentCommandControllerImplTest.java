package com.pricetag.producer.controller;

import com.pricetag.AbstractControllerStandaloneTest;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.producer.service.InstrumentCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class InstrumentCommandControllerImplTest extends AbstractControllerStandaloneTest {

    @InjectMocks
    InstrumentCommandControllerImpl instrumentCommandController;

    @Mock
    InstrumentCommandService commandService;

    @BeforeEach
    public void setup() {
        this.setup(instrumentCommandController);
    }


    @Test
    public void save_withValidRequest_ReturnSuccess() throws Exception {
        when(commandService.save(any())).thenReturn(Set.of(new InstrumentDTO()));
        mockMvc.perform(MockMvcRequestBuilders.post("/instruments/")
                        .content(getValidRequest())
                        .contentType(MediaType.APPLICATION_XML)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("200")));
    }


    @Test
    public void saveCacheable_withValidRequest_ReturnSuccess() throws Exception {

        when(commandService.saveCacheable(any())).thenReturn(new InstrumentDTO());
        mockMvc.perform(MockMvcRequestBuilders.post("/instruments/")
                        .content(getValidJsonRequest())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("200")));
    }

    @Test
    public void saveCacheable_withInvalidRequest_ReturnError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/instruments/")
                        .content(getInvalidJsonRequest())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Please fill name field.")));
    }

    private String getValidJsonRequest() {
        return "{\n" +
                "    \"name\": \"Z852\",\n" +
                "    \"price\": 12.20,\n" +
                "    \"vendor\": \"Vendor Pub1\"\n" +
                "}";
    }

    private String getInvalidJsonRequest() {
        return "{\n" +
                "    \"price\": 12.20,\n" +
                "    \"vendor\": \"Vendor Pub1\"\n" +
                "}";
    }

    private String getValidRequest() {
        return "<instruments>\n" +
                "<instrument>\n" +
                "<name>A123</name>\n" +
                "<price>1.0</price>\n" +
                "<vendor>Sample Pub</vendor>\n" +
                "</instrument>\n" +
                "<instrument>\n" +
                "<name>Z852</name>\n" +
                "<price>12.20</price>\n" +
                "<vendor>Vendor Pub1</vendor>\n" +
                "</instrument>\n" +
                "</instruments>";
    }




}