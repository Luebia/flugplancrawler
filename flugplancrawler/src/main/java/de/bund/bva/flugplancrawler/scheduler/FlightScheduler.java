package de.bund.bva.flugplancrawler.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import de.bund.bva.flugplancrawler.entities.SchedulerConfig;
import de.bund.bva.flugplancrawler.repository.SchedulerConfigRepository;
import de.bund.bva.flugplancrawler.service.ScrapperService;

/**
 * @author Bianca
 * Diese Klasse ist das Herzstück des automatisierten Suchprozesses
 */
@Component
public class FlightScheduler implements SchedulingConfigurer, Runnable {

    private static final Integer CANCEL_SCHEDULED_TASK_DELAY_THRESHOLD_IN_SECONDS = 60;

    @Autowired
    private TaskScheduler fooTaskScheduler;
    
    @Autowired
    private ScrapperService scrapperService;
    
    @Autowired
    private SchedulerConfigRepository repo;

    private Date nextExecutionTime = null;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    private ScheduledFuture<?> scheduledFuture;

    private Integer scheduleInMinutes = 60;

    /**
     * ruft eine Methode des ScrapperService auf, die den Suchlauf für
     * die Abflüge startet
     */
    @Override
    public void run() {
    	scrapperService.scrappAndStoreDepatures();
    }

    /**
     * konfigueriert das Suchintervall
     */
    @Override
    public synchronized void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

    	List<SchedulerConfig> configs = repo.findAll();
    	if(configs.size() > 0) {
    		scheduleInMinutes = configs.get(0).getMinutes();
    	}
    	 
    	if (this.scheduledTaskRegistrar == null) {
            this.scheduledTaskRegistrar = scheduledTaskRegistrar;
        }

        this.scheduledTaskRegistrar.setScheduler(fooTaskScheduler);

        scheduledFuture =
                this.scheduledTaskRegistrar
                        .getScheduler()
                        .schedule(
                                this,
                                triggerContext -> {
                                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                                    if (lastActualExecutionTime == null) {
                                        lastActualExecutionTime = new Date();
                                    }
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(lastActualExecutionTime);
                                    cal.add(Calendar.MINUTE, scheduleInMinutes);
                                    this.nextExecutionTime = cal.getTime();
                                    return nextExecutionTime;
                                });
    }

    /**
     * @param syncScheduleInMinutes
     * das neue Aktualisierungsintervall wird überprüft 
     */
    public synchronized void updateSchedule(Integer syncScheduleInMinutes) {
        this.scheduleInMinutes = syncScheduleInMinutes;
        long delayInSeconds = this.scheduledFuture.getDelay(TimeUnit.SECONDS);
        if (delayInSeconds < 0) {
            System.out.println("Suchlauf findet gerade statt. Neues Intervall wird danach übernommen.");
        } else if (delayInSeconds < CANCEL_SCHEDULED_TASK_DELAY_THRESHOLD_IN_SECONDS) {
            System.out.println("Nächster Suchlauf startet in weniger als {"+CANCEL_SCHEDULED_TASK_DELAY_THRESHOLD_IN_SECONDS+"} Sekunden. Neues Intervall wird nach dem nächsten Suchlauf automatisch übernommen.");
        } else {
            this.scheduledFuture.cancel(false); //do not interrupt the current run if it kicked in.
            configureTasks(this.scheduledTaskRegistrar);
        }
    }

    /**
     * @return Zeitpunkt für nächsten geplanten Suchlauf
     */
    public Date getNextSyncExecutionTime() {
        return nextExecutionTime;
    }

    /**
     * startet die run()-Methode
     */
    public void trigger() {
        run();
    }
}
