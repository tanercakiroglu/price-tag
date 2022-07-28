package com.pricetag.utils;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@UtilityClass
public class MapperUtils {

    public static Timestamp map(LocalDateTime value) {
        if (value == null)
            return null;
        return Timestamp.valueOf(value);

    }

    public static LocalDateTime map(Timestamp value) {
        if (value == null)
            return null;
        return value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
