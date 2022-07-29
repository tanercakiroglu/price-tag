package com.pricetag.consumer.service;

import com.pricetag.aspect.Loggable;
import com.pricetag.model.InstrumentDTO;
import com.pricetag.model.InstrumentDTOList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import javax.jms.Session;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;

import static com.pricetag.constant.Parameter.INSTRUMENTS_CACHE;
import static com.pricetag.constant.Parameter.INSTRUMENT_QUEUE;

@Service
@Log4j2
@Loggable
@RequiredArgsConstructor
public class JmsServiceImpl implements JmsService {

    private final RedisTemplate<String, InstrumentDTO> redisTemplate;


    @JmsListener(destination = INSTRUMENT_QUEUE)
    @Override
    public void receiveMessage(String xml, @Headers MessageHeaders headers,
                               Message message, Session session) throws JAXBException {
        var instruments = convertXmlToObject(xml);
        var now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        instruments.forEach(instrumentDTO -> {
            instrumentDTO.setCreatedDate(now);
            redisTemplate.opsForHash().put(INSTRUMENTS_CACHE, String.format("%s-%s-%s", now,
                    instrumentDTO.getName(), instrumentDTO.getVendor()), instrumentDTO);
        });
    }

    private Set<InstrumentDTO> convertXmlToObject(String xml) throws JAXBException {

        var sr = new StringReader(xml);
        var context = JAXBContext.newInstance(InstrumentDTOList.class);
        var un = context.createUnmarshaller();
        var instruments = (InstrumentDTOList) un.unmarshal(sr);
        return instruments.getInstrumentDTO();
    }
}
