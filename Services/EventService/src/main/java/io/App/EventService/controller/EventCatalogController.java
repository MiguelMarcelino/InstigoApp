package io.App.EventService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import io.App.EventService.dto.EventDTO;
import io.App.EventService.dto.EventListWrapper;
import io.App.EventService.dto.Pair;
import io.App.EventService.exceptions.EventAlreadyExistsException;
import io.App.EventService.exceptions.EventDoesNotExistException;
import io.App.EventService.exceptions.InternalAppException;

@RestController
@RequestMapping("/eventApi")
public class EventCatalogController {

	@Autowired
	private EventCatalog eC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@GetMapping("/eventCatalogList")
	public EventListWrapper eventList() {
		return this.eC.getAllEvents();
	}

	@GetMapping("getEventsFromCommunity/{cID}")
	public ResponseEntity<Pair<String, EventListWrapper>> eventsFromCommunity(@PathVariable("cID") int cID) {
		EventListWrapper eLW = null;
		try {
			eLW = this.eC.getEventsFromCommunity(cID);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), eLW),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("Successfull events request");
		return new ResponseEntity<>(new Pair<>("Successfull events request", eLW),
				HttpStatus.OK);
	}

	@PostMapping("registerNewEvent/event")
	public ResponseEntity<String> registerNewEvent(
			@RequestBody String eventJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		EventDTO eDTO = null;

		try {
			eDTO = objectMapper.readValue(eventJSON, EventDTO.class);
			this.eC.registerNewEvent(new Event(Integer.parseInt(eDTO.getId()),
					eDTO.getName(), eDTO.getStart(), eDTO.getEnd(),
					Integer.parseInt(eDTO.getcID()), eDTO.getcName()));
		} catch (JsonParseException | JsonMappingException
				| NumberFormatException | EventAlreadyExistsException
				| InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully registered new Event");
		return new ResponseEntity<>("Successfully registered new Event",
				HttpStatus.OK);
	}

	@PostMapping("deleteEvent/event")
	public ResponseEntity<String> deleteEvent(@RequestBody String eventJSON)
			throws EventDoesNotExistException {
		ObjectMapper objectMapper = new ObjectMapper();
		EventDTO eDTO = null;

		try {
			eDTO = objectMapper.readValue(eventJSON, EventDTO.class);
			this.eC.deleteEvent(new Event(Integer.parseInt(eDTO.getId()),
					eDTO.getName(), eDTO.getStart(), eDTO.getEnd(),
					Integer.parseInt(eDTO.getcID()), eDTO.getcName()));
		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully deleted the Event");
		return new ResponseEntity<>("Successfully deleted the Event",
				HttpStatus.OK);
	}
}
