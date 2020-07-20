package io.App.EventService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.EventService.databaseConnection.UserEventDatabaseManagement;
import io.App.EventService.dto.EventListWrapper;

@RestController
@RequestMapping("/userEventApi")
public class UserEventController {

	private UserEventDatabaseManagement uEDM;

	public UserEventController() {
		// Empty Constructor
	}

	@GetMapping(path = "/eventsFromSubbedCommunities/{uID}")
	public EventListWrapper eventsFromSubbedCommunities(@PathVariable("uID") int uID) {
		return uEDM.eventsFromSubCommunities(uID);
	}
}
