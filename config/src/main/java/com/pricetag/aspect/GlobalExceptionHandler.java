package com.pricetag.aspect;


import com.pricetag.MessageSource;
import com.pricetag.constant.ErrorCodes;
import com.pricetag.response.WrapperResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@ControllerAdvice
@ResponseBody
@Log4j2
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public WrapperResponse generalExceptionHandler(Exception ex, WebRequest request) {

        log.error("Exception  ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(List.of(ex.getMessage() != null ? ex.getMessage(): "Something went wrong"))
                .build();
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public WrapperResponse resourceNotFoundException(Exception ex, WebRequest request) {

        var errorMessages = messageSource.getMessage(ErrorCodes.RESOURCE_NOT_FOUND, null, request.getLocale());
        log.error("ResourceNotFoundException ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .errorMessage(List.of(errorMessages))
                .build();
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public WrapperResponse resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {

        var errorMessages = messageSource.getMessage(ErrorCodes.ENTITY_NOT_FOUND, new String[]{ex.getMessage()}, request.getLocale());
        log.error("EntityNotFoundException ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .errorMessage(List.of(errorMessages))
                .build();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public WrapperResponse illegalArgumentExceptionErrorHandler(Exception ex) {
        log.error("IllegalArgumentException ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(List.of(ex.getMessage()))
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected WrapperResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        var errorMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(message -> messageSource.getMessage(Objects.requireNonNull(message.getDefaultMessage()), null, request.getLocale()))
                .collect(Collectors.toList());
        log.error("MethodArgumentNotValidException ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(errorMessages)
                .build();
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public WrapperResponse httpMessageNotReadableExceptionErrorHandler(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException ", ex);
        return WrapperResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorMessage(List.of(ex.getMessage()))
                .build();
    }


}
