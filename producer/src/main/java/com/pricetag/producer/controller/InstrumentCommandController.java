package com.pricetag.producer.controller;


import com.pricetag.model.InstrumentDTO;
import com.pricetag.response.WrapperCollectionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.JAXBException;

public interface InstrumentCommandController {

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    WrapperCollectionResponse<InstrumentDTO> save(@RequestBody @NotBlank String request) throws JAXBException;

}
