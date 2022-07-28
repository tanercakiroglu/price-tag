package com.pricetag.consumer.batch;

import com.pricetag.model.InstrumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import static com.pricetag.constant.Parameter.INSTRUMENTS_CACHE;

@Service
@RequiredArgsConstructor
@Log4j2
public class InstrumentDeleteJob {

    private final RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0/1 * * * *")
    public void scheduleTaskUsingCronExpression() {
        var hashOps = (HashOperations<String, String, InstrumentDTO>) redisTemplate.opsForHash();
        var entries = hashOps.entries(INSTRUMENTS_CACHE);
        var thirtyDaysAgo = LocalDateTime.now().minusDays(30).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        var deletedCount= hashOps.delete(INSTRUMENTS_CACHE, entries.entrySet()
                .stream()
                .filter(entrySet -> entrySet.getValue().getCreatedDate() < thirtyDaysAgo)
                .map(Map.Entry::getKey)
                .distinct()
                .toArray());
       log.info("Expired instrument count :" + deletedCount);

    }
}
