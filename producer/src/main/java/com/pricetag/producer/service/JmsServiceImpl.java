package com.pricetag.producer.service;


import com.pricetag.aspect.Loggable;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.model.InstrumentDTOList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.util.Set;

import static com.pricetag.constant.Parameter.INSTRUMENT_QUEUE;

;

@Service
@RequiredArgsConstructor
@Log4j2
@Loggable
public class JmsServiceImpl implements JmsService {

    private final JmsTemplate jmsTemplate;

    @Override
    public Set<InstrumentDTO> send(String instruments) throws JAXBException {
        log.info("sending with convertAndSend() to queue <" + instruments + ">");
        jmsTemplate.convertAndSend(INSTRUMENT_QUEUE, instruments);
        return convertXmlToObject(instruments);
    }

   private Set<InstrumentDTO> convertXmlToObject(String xml) throws JAXBException {

        var sr = new StringReader(xml);
        var context = JAXBContext.newInstance(InstrumentDTOList.class);
        var un = context.createUnmarshaller();
        var instruments = (InstrumentDTOList) un.unmarshal(sr);
        return instruments.getInstrumentDTO();

    }
}
