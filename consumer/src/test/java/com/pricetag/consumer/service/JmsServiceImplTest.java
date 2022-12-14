package com.pricetag.consumer.service;

import com.pricetag.model.InstrumentDTO;
import org.apache.activemq.ActiveMQSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;

import javax.xml.bind.JAXBException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JmsServiceImplTest {

    @InjectMocks
    JmsServiceImpl jmsService;

    @Mock
    RedisTemplate<String, InstrumentDTO> redisTemplate;

    @Mock
    HashOperations hashOperations;

    @Mock
    ActiveMQSession activeMQSession;


    @Test
    public void receiveMessage_whenValidXml_ReturnSuccess() throws JAXBException {

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        doNothing().when(hashOperations).put(any(), any(), any());
        jmsService.receiveMessage(geXml(), new MessageHeaders(new HashMap<>()), new GenericMessage(""), activeMQSession);
        verify(hashOperations, times(2)).put(any(), any(), any());
    }

    @Test
    public void receiveMessage_whenInvalidXml_throwJaxbException() throws JAXBException {

        assertThrows(JAXBException.class, () ->
                jmsService.receiveMessage("geXml()",
                        new MessageHeaders(new HashMap<>()), new GenericMessage(""), activeMQSession));
    }

    private String geXml() {
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