package com.pricetag.consumer.controller;

import com.pricetag.aspect.Loggable;
import com.pricetag.consumer.service.InstrumentQueryService;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.response.WrapperCollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instruments")
@Loggable
@RequiredArgsConstructor
public class InstrumentQueryControllerImpl implements InstrumentQueryController{

    private final InstrumentQueryService instrumentQueryService;

    @Override
    public WrapperCollectionResponse<InstrumentDTO> getAll() {
        return WrapperCollectionResponse.of(instrumentQueryService.getAll());
    }

    @Override
    public WrapperCollectionResponse<InstrumentDTO> getAllByVendorOrName(String vendorOrName) {
        return WrapperCollectionResponse.of(instrumentQueryService.getAllByVendorOrName(vendorOrName));
    }
}
