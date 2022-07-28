package com.pricetag.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
public class WrapperCollectionResponse<T> extends AbstractResponse {

    private Collection<T> data;
    private long totalSize;

    private static final WrapperCollectionResponse<?> EMPTY = new WrapperCollectionResponse<>();


    private WrapperCollectionResponse() {
        this.data = Collections.emptyList();
    }


    public static <T> WrapperCollectionResponse<T> empty() {
        @SuppressWarnings("unchecked")
        WrapperCollectionResponse<T> t = (WrapperCollectionResponse<T>) EMPTY;
        return t;
    }

    private WrapperCollectionResponse(Collection<T> value) {
        this.data = CollectionUtils.isEmpty(value) ? Collections.emptyList() : value;
        this.totalSize = CollectionUtils.isEmpty(value) ? 0L : value.size();
        this.status = HttpStatus.OK.value();
    }

    public static <T> WrapperCollectionResponse<T> of(Collection<T> value) {
        return new WrapperCollectionResponse<>(value);
    }

    public static <T> WrapperCollectionResponse<T> ofNullable(Collection<T> value) {
        return value == null ? empty() : of(value);
    }

    public Collection<T> get() {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        return data;
    }


}
