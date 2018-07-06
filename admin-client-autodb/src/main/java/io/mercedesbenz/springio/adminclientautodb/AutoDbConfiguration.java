package io.mercedesbenz.springio.adminclientautodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AutoDbConfiguration {
   
   @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}
