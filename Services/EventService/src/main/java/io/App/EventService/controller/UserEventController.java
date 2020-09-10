package io.App.EventService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.EventService.business.catalogs.EventSubscriptionCatalog;
import io.App.EventService.business.exceptions.InternalAppException;
import io.App.EventService.business.mappers.EventMapper;
import io.App.EventService.facade.dto.EventListWrapper;
import io.App.EventService.facade.dto.Pair;

@RestController
@RequestMapping("/userEventApi")
public class UserEventController {

	@Autowired
	private EventSubscriptionCatalog uEC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	public UserEventController() {
		// Empty Constructor
	}

	@GetMapping(path = "/eventsFromSubbedCommunities/{uID}")
	public ResponseEntity<Pair<String, EventListWrapper>> eventsFromSubbedCommunities(
			@PathVariable("uID") String uID) {
		EventListWrapper eLW = null;

		try {
			eLW = new EventListWrapper(
					EventMapper.eventListToEventDTOListMapper(
							uEC.eventsFromSubbedCommunities(
									Integer.parseInt(uID))));
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfull subscribed events request");
		return new ResponseEntity<>(
				new Pair<>("Successfull events request", eLW), HttpStatus.OK);
	}

}
