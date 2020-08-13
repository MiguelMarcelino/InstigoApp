package io.App.EventService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.EventService.EventComponent.UserEventCatalog;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.dto.Pair;
import io.App.EventService.exceptions.InternalAppException;

@RestController
@RequestMapping("/userEventApi")
public class UserEventController {

	@Autowired
	private UserEventCatalog uEC;
	
	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	public UserEventController() {
		// Empty Constructor
	}

	@GetMapping(path = "/eventsFromSubbedCommunities/{uID}")
	public ResponseEntity<Pair<String, EventListWrapper>> eventsFromSubbedCommunities(
			@PathVariable("uID") String uID) {
		EventListWrapper eLW = null;

		try {
			eLW = new EventListWrapper(uEC.eventsFromSubbedCommunities(Integer.parseInt(uID)));
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("Successfull subscribed events request");
		return new ResponseEntity<>(new Pair<>("Successfull events request", eLW),
				HttpStatus.OK);
	}
	
	@GetMapping(path = "/userCreatedEvents/{userName}")
	public ResponseEntity<Pair<String, EventListWrapper>> userCreatedEvents(@PathVariable("userName") String userName) {
		EventListWrapper eLW = null;
		System.out.println(userName);
		try {
			eLW = new EventListWrapper(uEC.eventsCreatedByUser(userName));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("Successfull created events request");
		return new ResponseEntity<>(new Pair<>("Successfull created events request", eLW),
				HttpStatus.OK);
	}
}
