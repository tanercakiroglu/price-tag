package com.pricetag.producer.service;


import com.pricetag.aspect.Loggable;
import com.pricetag.model.InstrumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Log4j2
@Loggable
public class InstrumentCommandServiceImpl implements InstrumentCommandService {

    private final JmsService jmsService;

    @Override
    public Set<InstrumentDTO> save(String request) throws JAXBException {
        return jmsService.send(request);
    }

}
