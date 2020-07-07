package io.App.EventService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.App.EventService.EventComponent.Event;
import io.App.EventService.EventComponent.EventCatalog;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.EventDoesNotExistException;
import io.App.EventService.exceptions.NoEventsFromCommunityException;

@RestController
public class EventCatalogController {
	
	@Autowired
	private EventCatalog eC;

	@GetMapping("/eventCatalogList")
	public EventListWrapper eventList() {
		return this.eC.getAllEvents();
	}
	
	@GetMapping("getEventsFromCommunity/{cID}")
	public EventListWrapper eventsFromCommunity(@PathVariable("cID") int cID) throws NoEventsFromCommunityException {
		return this.eC.getEventsFromCommunity(cID);
	}

	@PostMapping("registerNewEvent/event")
	public void registerNewEvent(@RequestBody Event event) throws EventAlreadyExistsException {
		this.eC.registerNewEvent(event);
	}
	
	@PostMapping("deleteEvent/event")
	public void deleteEvent(@RequestBody Event event) throws EventDoesNotExistException {
		this.eC.deleteEvent(event);
	}
}
