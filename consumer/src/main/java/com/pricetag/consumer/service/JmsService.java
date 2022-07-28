package com.pricetag.consumer.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import javax.jms.Session;
import javax.xml.bind.JAXBException;

import static com.pricetag.constant.Parameter.INSTRUMENT_QUEUE;

public interface JmsService {

    @JmsListener(destination = INSTRUMENT_QUEUE)
    void receiveMessage(@Payload String xml,
                        @Headers MessageHeaders headers,
                        Message message, Session session) throws JAXBException;
}
