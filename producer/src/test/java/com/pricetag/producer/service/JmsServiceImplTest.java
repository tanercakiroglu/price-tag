package com.pricetag.producer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class JmsServiceImplTest {

    @InjectMocks
    JmsServiceImpl jmsService;

    @Mock
    JmsTemplate jmsTemplate;

    @Test
    public void send_whenValidParameter_returnSuccess() throws JAXBException {
        jmsService.send(getXml());
        verify(jmsTemplate,times(1)).convertAndSend(anyString(), (Object) any());
    }

    @Test
    public void send_whenInvalidParameter_throwJaxbException() {
        assertThrows(JAXBException.class,()->jmsService.send(""));
    }

    private String getXml() {
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