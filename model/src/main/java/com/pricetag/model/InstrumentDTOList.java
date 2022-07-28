package com.pricetag.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "instruments")
@XmlType(name = "instruments",propOrder = {
        "instrumentDTO"
})
public class InstrumentDTOList {

    @XmlElement(name = "instrument")
    private Set<InstrumentDTO> instrumentDTO;
    private static final long serialVersionUID = 5749048342594025747L;
}
