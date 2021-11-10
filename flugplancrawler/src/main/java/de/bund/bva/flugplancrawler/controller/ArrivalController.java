package de.bund.bva.flugplancrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bianca
 * Den Controller habe ich bereits für die Kommunikation mit der Abflug Seite vorbereitet.
 */
@Controller
public class ArrivalController {

//	@Autowired
//	private ArrivalRepository arrivalRepository;
//	
//	@RequestMapping(
//			method = RequestMethod.GET,
//			path = "/arrivals",
//			produces = MediaType.APPLICATION_JSON_VALUE
//			)
//	public List<Arrival> getArrivals(){
//		
//		List<Arrival> arrivalList = arrivalRepository.findAll();
//		return arrivalList;
//	}
	
	
	 /**
	 * @return Abflug Dummy Seite
	 * diese Methode dient nur dem Aufruf der Dummy Seite
	 */
	@GetMapping("/arrivals")
	public String arrivalsDummyPage() {
		return "arrivals_index";
	}
}
