package io.App.EventService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.EventService.EventComponent.Event;
import io.App.EventService.EventComponent.EventCatalog;
import io.App.EventService.EventComponent.EventMapper;
import io.App.EventService.EventComponent.UserAuthorizationCheck;
import io.App.EventService.dto.EventDTO;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.dto.Pair;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.InternalAppException;
import io.App.EventService.exceptions.NonExistantOperationException;
import io.App.EventService.exceptions.UserDoesNotExistException;
import io.App.EventService.exceptions.UserNotAuthorizedException;

@RestController
@RequestMapping("/eventApi")
public class EventCatalogController {

	@Autowired
	private EventCatalog eC;
	@Autowired
	private UserAuthorizationCheck uAC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@GetMapping("/events")
	public ResponseEntity<Pair<String, EventListWrapper>> eventList() {
		EventListWrapper eLW = null;
		try {
			eLW = new EventListWrapper(EventMapper
					.eventListToEventDTOListMapper(eC.getAllEvents()));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfull events request");
		return new ResponseEntity<>(
				new Pair<>("Successfull events request", eLW), HttpStatus.OK);
	}

	@GetMapping("getEventsFromCommunity/{cID}")
	public ResponseEntity<Pair<String, EventListWrapper>> eventsFromCommunity(
			@PathVariable("cID") int cID) {
		EventListWrapper eLW = null;
		try {
			eLW = new EventListWrapper(
					EventMapper.eventListToEventDTOListMapper(
							eC.getEventsFromCommunity(cID)));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfull events request");
		return new ResponseEntity<>(
				new Pair<>("Successfull events request", eLW), HttpStatus.OK);
	}

	@PostMapping(path = "event/create", consumes = {
			"application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerNewEvent(
			@RequestBody String eventJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		EventDTO eDTO = null;

		try {
			eDTO = objectMapper.readValue(eventJSON, EventDTO.class);

			// check user authorization to perform a creation
			uAC.checkCreateAuthorization(eDTO.getCurrentUser());

			this.eC.registerNewEvent(new Event(eDTO.getName(), eDTO.getStart(),
					eDTO.getEnd(), eDTO.getCommunity(), eDTO.getEventOwner()));

		} catch (JsonParseException | JsonMappingException
				| NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (EventAlreadyExistsException | InternalAppException
				| UserDoesNotExistException | UserNotAuthorizedException
				| NonExistantOperationException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		System.out.println("Successfully registered new Event");
		return new ResponseEntity<>("Successfully registered new Event",
				HttpStatus.CREATED);
	}

	@PostMapping("event/delete")
	public ResponseEntity<String> deleteEvent(@RequestBody String eventJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		EventDTO eDTO = null;

		try {
			eDTO = objectMapper.readValue(eventJSON, EventDTO.class);

			// check user authorization to perform a creation
			uAC.checkDeleteAuthorization(eDTO.getEventOwner(),
					eDTO.getCurrentUser());

			this.eC.deleteEvent(new Event(eDTO.getId(), eDTO.getName(),
					eDTO.getStart(), eDTO.getEnd(), eDTO.getCommunity(),
					eDTO.getEventOwner()));
		} catch (JsonParseException | JsonMappingException
				| NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException | UserDoesNotExistException
				| UserNotAuthorizedException
				| NonExistantOperationException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		System.out.println("Successfully deleted the Event");
		return new ResponseEntity<>("Successfully deleted the Event",
				HttpStatus.OK);
	}

	@GetMapping(path = "/userCreatedEvents/{userName}")
	public ResponseEntity<Pair<String, EventListWrapper>> userCreatedEvents(
			@PathVariable("userName") String userName) {
		EventListWrapper eLW = null;
		System.out.println(userName);
		try {
			eLW = new EventListWrapper(
					EventMapper.eventListToEventDTOListMapper(
							eC.eventsCreatedByUser(userName)));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfull created events request");
		return new ResponseEntity<>(
				new Pair<>("Successfull created events request", eLW),
				HttpStatus.OK);
	}
}
