package io.App.EventService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventCatalogTest {

	@RequestMapping("/eventCatalogList")
	public EventListWrapper eventList() {
		EventCatalog eC = new EventCatalog();
		Event e1 = new Event(1, "Reunião", "24-2-2020,14:00", "24-2-2020,16:00", 1, "ASC");
		Event e2 = new Event(2, "Almoço no LASIGE", "14-3-2020,13:00", "14-3-2020,15:30", 2, "LASIGE");
		eC.registerNewEvent(e1);
		eC.registerNewEvent(e2);
		return eC.getAllEvents();
	}
}
