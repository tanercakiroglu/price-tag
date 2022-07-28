package com.pricetag.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instrument",propOrder = {
        "name",
        "price",
        "vendor"
})
public class InstrumentDTO implements Serializable {

    private static final long serialVersionUID = 4294828978771772354L;
    @XmlElement
    @NotBlank(message = "name.invalid")
    private String name;
    @XmlElement
    @NotNull(message = "price.invalid")
    private BigDecimal price;
    @XmlElement
    @NotNull(message = "vendor.invalid")
    private String vendor;
    @XmlTransient
    private Long createdDate;

}
