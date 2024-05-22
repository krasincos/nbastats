package security.skyhawk.nbastats.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NbaStatsConsumer {

    ObjectMapper mapper = new ObjectMapper();
    NbaStatsService service;

    @RabbitListener(queues = "playerStatsQueue")
    public void savePlayerStats(String message) {
        try {
            var stats = mapper.readValue(message, PlayerStatsDto.class);
            log.info("got player stats: {}", stats);
            service.savePlayerStats(stats);
        } catch (JsonProcessingException e) {
            log.error("deserialization error {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}