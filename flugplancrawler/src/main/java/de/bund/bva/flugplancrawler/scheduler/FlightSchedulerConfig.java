package de.bund.bva.flugplancrawler.scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Bianca
 * Diese Klasse konfiguriert den FlightScheduler
 */
@Configuration
public class FlightSchedulerConfig {

    @Bean
    @Primary
    public TaskScheduler fooTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("FLIGHT_SCHEDULER");
        scheduler.setPoolSize(5);
        scheduler.setAwaitTerminationSeconds(210);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setRemoveOnCancelPolicy(true);
        scheduler.initialize();
        return scheduler;
    }
}
