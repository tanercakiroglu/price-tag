package com.pricetag.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
public class WrapperResponse<T> extends AbstractResponse {

    private T data;

    private static final WrapperResponse<?> EMPTY = new WrapperResponse<>();


    private WrapperResponse() {
        this.data = null;
        this.status= HttpStatus.OK.value();
    }


    public static <T> WrapperResponse<T> empty() {
        @SuppressWarnings("unchecked")
        WrapperResponse<T> t = (WrapperResponse<T>) EMPTY;
        return t;
    }

    private WrapperResponse(T value) {
        this.data = Objects.requireNonNull(value);
        this.status= HttpStatus.OK.value();
    }

    public static <T> WrapperResponse<T> of(T value) {
        return new WrapperResponse<>(value);
    }

    public static <T> WrapperResponse<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        if (data == null) {
            throw new NoSuchElementException("No value present");
        }
        return data;
    }

}
