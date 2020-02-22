package io.App.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventCatalogController {
	
	@Autowired
	private EventCatalog eC;

	@RequestMapping("/eventCatalogList")
	public EventListWrapper eventList() {
		return this.eC.getAllEvents();
	}
	
	@RequestMapping("getEventsFromCommunity/{cID}")
	public EventListWrapper EventsFromCommunity(@PathVariable("cID") int cID) {
		return this.eC.getEventsFromCommunity(cID);
	}

	@PostMapping("registerNewEvent/event")
	public void registerNewEvent(Event event) {
		this.eC.registerNewEvent(event);
	}
	
	@PostMapping("deleteEvent/event")
	public void deleteEvent(Event event) {
		this.eC.deleteEvent(event);
	}
}
