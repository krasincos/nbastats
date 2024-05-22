package security.skyhawk.nbastats.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NbaStatsService {

    RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    public void logStats(PlayerStatsDto stats) {
        try {
            rabbitTemplate.convertAndSend("playerStatsQueue", objectMapper.writeValueAsBytes(stats));
            log.info("stats sent to playerStatsQueue: {}", stats);
        } catch (JsonProcessingException e) {
            log.error("can't serialize", e);
            throw new RuntimeException(e);
        }
    }

}