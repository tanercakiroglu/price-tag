package com.pricetag.producer.service;


import com.pricetag.model.InstrumentDTO;

import javax.xml.bind.JAXBException;
import java.util.Set;

public interface InstrumentCommandService {
    Set<InstrumentDTO> save(String xml) throws JAXBException;


    InstrumentDTO saveCacheable(InstrumentDTO instrumentDTO);
}
