package hr.fer.config;

import org.alicebot.ab.Bot;
import org.alicebot.ab.configuration.BotConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class BotConfig {

    @Bean
    public Bot getBot() {
        return new Bot(BotConfiguration.builder()
            .name("john")
            .path("src/main/resources")
            .build());
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(2);
    }

}
