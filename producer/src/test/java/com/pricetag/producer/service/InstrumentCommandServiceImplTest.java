package com.pricetag.producer.service;

import com.pricetag.model.InstrumentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstrumentCommandServiceImplTest {

    @InjectMocks
    InstrumentCommandServiceImpl instrumentCommandService;

    @Mock
    RedisTemplate redisTemplate;

    @Mock
    HashOperations hashOperations;


    @Test
    public void saveCacheable_whenValidRequest_TimestampShouldBeAdded() {
        var instrumentDto = new InstrumentDTO();
        instrumentDto.setName("abc");
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        doNothing().when(hashOperations).put(any(),any(),any());
        var serviceResult =instrumentCommandService.saveCacheable(instrumentDto);
        assertNotNull(serviceResult.getCreatedDate());
    }

}