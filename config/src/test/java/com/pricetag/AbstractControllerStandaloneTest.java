package com.pricetag;

import com.pricetag.aspect.GlobalExceptionHandler;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class AbstractControllerStandaloneTest {

    protected MockMvc mockMvc;

    public void setup(Object testedController) {

        mockMvc = MockMvcBuilders.standaloneSetup(testedController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setControllerAdvice(new GlobalExceptionHandler(new MessageSource()))
                .build();
    }

}
