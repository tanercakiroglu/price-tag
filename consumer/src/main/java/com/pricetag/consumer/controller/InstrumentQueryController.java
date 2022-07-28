package com.pricetag.consumer.controller;

import com.pricetag.model.InstrumentDTO;
import com.pricetag.response.WrapperCollectionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface InstrumentQueryController {

    @GetMapping("/")
    WrapperCollectionResponse<InstrumentDTO> getAll();

    @GetMapping("/{searchParam}")
    WrapperCollectionResponse<InstrumentDTO> getAllByVendorOrName(@PathVariable("searchParam") String vendorOrName);
}
