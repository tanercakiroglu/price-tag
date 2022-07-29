package com.pricetag.producer.service;

import com.pricetag.model.InstrumentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstrumentQueryServiceImplTest {

    @InjectMocks
    InstrumentQueryServiceImpl instrumentQueryService;

    @Mock
    RedisTemplate redisTemplate;

    @Mock
    HashOperations hashOperations;
    @Mock
    Cursor<InstrumentDTO> cursor;

    @Test
    public void getAll_returnSuccess(){
        when( redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.entries(any())).thenReturn(new HashMap<String,InstrumentDTO>(){{
            put("abc",new InstrumentDTO());
        }});

       var serviceResult = instrumentQueryService.getAll();

       assertEquals(1,serviceResult.size());

    }

    @Test
    public void getAllByVendorOrName_returnSuccess(){
        when( redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.scan(any(),any())).thenReturn(cursor);

        var serviceResult = instrumentQueryService.getAllByVendorOrName("any()");
        assertNotNull(serviceResult);
    }

}