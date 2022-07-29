package com.pricetag.producer.controller;

import com.pricetag.AbstractControllerStandaloneTest;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.producer.service.InstrumentQueryService;
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
class InstrumentQueryControllerImplTest extends AbstractControllerStandaloneTest {

    @InjectMocks
    InstrumentQueryControllerImpl instrumentQueryController;

    @Mock
    InstrumentQueryService instrumentQueryService;

    @BeforeEach
    public void setup() {
        this.setup(instrumentQueryController);
    }


    @Test
    public void  getAll_returnSuccessResponse() throws Exception {
        when(instrumentQueryService.getAll()).thenReturn(Set.of(new InstrumentDTO()));
        mockMvc.perform(MockMvcRequestBuilders.get("/instruments/")
                        .contentType(MediaType.APPLICATION_XML)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("200")));
    }

    @Test
    public void  getAllByVendorOrName_returnSuccessResponse() throws Exception {
        when(instrumentQueryService.getAllByVendorOrName(any())).thenReturn(Set.of(new InstrumentDTO()));
        mockMvc.perform(MockMvcRequestBuilders.get("/instruments/Abc")
                        .contentType(MediaType.APPLICATION_XML)
                )
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString("200")));
    }

}