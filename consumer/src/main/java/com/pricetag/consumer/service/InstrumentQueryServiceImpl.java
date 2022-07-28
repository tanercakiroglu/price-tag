package com.pricetag.consumer.service;

import com.pricetag.model.InstrumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pricetag.constant.Parameter.INSTRUMENTS_CACHE;

@Service
@RequiredArgsConstructor
public class InstrumentQueryServiceImpl implements InstrumentQueryService {

    private final RedisTemplate redisTemplate;

    @Override
    public Collection<InstrumentDTO> getAll() {
        var storedData = (HashMap<String, InstrumentDTO>) redisTemplate.opsForHash().entries(INSTRUMENTS_CACHE);
        return storedData.values();
    }

    @Override
    public Collection<InstrumentDTO> getAllByVendorOrName(String vendorOrName) {
        var hashOps = (HashOperations<String, String, InstrumentDTO>) redisTemplate.opsForHash();
        var cursor = hashOps.scan(INSTRUMENTS_CACHE,
                ScanOptions.scanOptions()
                        .match(String.format("*%s*", vendorOrName))
                        .build());

        return cursor.stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }
}
