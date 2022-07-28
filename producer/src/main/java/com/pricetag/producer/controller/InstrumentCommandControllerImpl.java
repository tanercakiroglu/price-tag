package com.pricetag.producer.controller;


import com.pricetag.aspect.Loggable;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.producer.service.InstrumentCommandService;
import com.pricetag.response.WrapperCollectionResponse;
import com.pricetag.response.WrapperResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;


@RestController
@RequestMapping("/instruments")
@Loggable
@RequiredArgsConstructor
public class InstrumentCommandControllerImpl implements InstrumentCommandController {

    private final InstrumentCommandService instrumentCommandService;

    @Override
    public WrapperCollectionResponse<InstrumentDTO> save(String request) throws JAXBException {
        return WrapperCollectionResponse.of(instrumentCommandService.save(request));
    }

    @Override
    public WrapperResponse<InstrumentDTO> saveCacheable(InstrumentDTO request)  {
        return WrapperResponse.of(instrumentCommandService.saveCacheable(request));
    }

}
