package com.pricetag.producer.service;


import com.pricetag.aspect.Loggable;
import com.pricetag.model.InstrumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import static com.pricetag.constant.Parameter.INSTRUMENTS_CACHE;


@Service
@RequiredArgsConstructor
@Log4j2
@Loggable
public class InstrumentCommandServiceImpl implements InstrumentCommandService {

    private final JmsService jmsService;
    private final RedisTemplate redisTemplate;

    @Override
    public Set<InstrumentDTO> save(String request) throws JAXBException {
        return jmsService.send(request);
    }

    @Override
    public InstrumentDTO saveCacheable(InstrumentDTO instrumentDTO) {
        var now =  LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        instrumentDTO.setCreatedDate(now);
        redisTemplate.opsForHash().put(INSTRUMENTS_CACHE,String.format("%s-%s-%s-%s",now,
                instrumentDTO.getName(),instrumentDTO.getPrice(),instrumentDTO.getVendor()),instrumentDTO);
        return instrumentDTO;
    }

}
