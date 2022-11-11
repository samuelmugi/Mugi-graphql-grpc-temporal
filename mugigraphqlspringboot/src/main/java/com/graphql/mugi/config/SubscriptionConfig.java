package com.graphql.mugi.config;

import com.graphql.mugi.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

/**
 * @author Mugi
 */
@Configuration
public class SubscriptionConfig {
    @Bean
    public Sinks.Many<User> userSink() {
        return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    @Bean
    public Flux<User> userFlux(Sinks.Many<User> userSink) {
        return userSink.asFlux();
    }
}
