package security.skyhawk.nbastats.logger;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue playerStatsQueue() {
        return new Queue("playerStatsQueue", true);
    }
}