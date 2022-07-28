package com.pricetag.producer.service;


import com.pricetag.model.InstrumentDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

import javax.jms.Session;
import javax.xml.bind.JAXBException;
import java.util.Set;

import static com.pricetag.constant.Parameter.INSTRUMENT_QUEUE;

public interface JmsService {

    Set<InstrumentDTO> send(String instrument) throws JAXBException;

    @JmsListener(destination = INSTRUMENT_QUEUE)
    void receiveMessage(String xml, @Headers MessageHeaders headers,
                        Message message, Session session) throws JAXBException;
}
