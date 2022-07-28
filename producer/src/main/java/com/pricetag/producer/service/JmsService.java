package com.pricetag.producer.service;


import com.pricetag.model.InstrumentDTO;

import javax.xml.bind.JAXBException;
import java.util.Set;

public interface JmsService {

    Set<InstrumentDTO> send(String instrument) throws JAXBException;

}
