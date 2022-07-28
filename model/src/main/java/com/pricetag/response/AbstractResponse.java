package com.pricetag.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractResponse {

    protected int status;
    protected LocalDateTime timestamp;
    protected List<String> errorMessage;
    protected List<String> warningMessage;
    protected List<String> infoMessage;
}
