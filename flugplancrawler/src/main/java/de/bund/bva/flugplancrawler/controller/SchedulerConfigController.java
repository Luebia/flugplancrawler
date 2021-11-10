package de.bund.bva.flugplancrawler.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.bund.bva.flugplancrawler.entities.SchedulerConfig;
import de.bund.bva.flugplancrawler.service.SchedulerConfigService;

/**
 * @author Bianca
 * Der Controller dient der Steuerung der Funktionen auf der Suchlauf Seite im Browser.
 */
@Controller
public class SchedulerConfigController {
	
	@Autowired 
	private SchedulerConfigService schedulerService;
	
    /**
     * @param config
     * @return Suchlauf Seite wird neu geladen
     * speichert das aktualisierte Suchlaufintervall in der DB
     */
    @PostMapping("/saveJobConfig")
	public String saveJobConfig(@ModelAttribute("jobConfig") SchedulerConfig config) {
		SchedulerConfig schedulerConfig = schedulerService.getSchedulerConfigById(config.getId());
		schedulerConfig.setMinutes(config.getMinutes());
		schedulerService.saveSchedulerConfig(schedulerConfig);
		schedulerService.reSchedule(schedulerConfig.getMinutes());
		return "redirect:/scheduler";
	}
	
    /**
     * @param model
     * @return Suchlauf Seite
     * Aufruf der Suchlauf Seite über das Menü
     */
    @GetMapping("/scheduler")
	public String config(Model model) {
		// SchedulerConfig laden -> Falls kein Eintrag in der DB vorhanden ist, wird ein Eintrag 
    	// mit 60 Minuten erzeugt.
		List<SchedulerConfig> sConfigs = schedulerService.getAllSchedulerConfigs();
		if(sConfigs.size() == 0) {
			SchedulerConfig schedulerConfig = new SchedulerConfig();
			schedulerConfig.setMinutes(60);
			schedulerService.saveSchedulerConfig(schedulerConfig);
		}
		sConfigs = schedulerService.getAllSchedulerConfigs();
		model.addAttribute("jobConfig", sConfigs.get(0));
		model.addAttribute("nextSchedule", schedulerService.getNextSyncExecutionTime());
		return "scheduler_index";
	}
    
    /**
     * @return Ablug Seite
     * führt einen manuellen Suchlauf aus. Anschließend wird man auf die aktualisierte 
     * Abflug seite geleitet
     */
    @GetMapping("/search")
    public String manuallySearch() {
        schedulerService.triggerScheduler();
        return "departures_index";
    }

}
