package com.pricetag.consumer.service;

import com.pricetag.model.InstrumentDTO;

import java.util.Collection;

public interface InstrumentQueryService {

    Collection<InstrumentDTO> getAll();

    Collection<InstrumentDTO> getAllByVendorOrName(String vendor);

}
