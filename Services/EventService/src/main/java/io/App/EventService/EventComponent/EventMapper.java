package io.App.EventService.EventComponent;

import java.util.ArrayList;
import java.util.List;

import io.App.EventService.dto.EventDTO;

/**
 * This class maps an EventDTO or a list of EventDTO to events and vice versa
 * 
 * @author miguel
 *
 */
public class EventMapper {

	public static EventDTO eventToEventDTOMapper(Event event) {
		EventDTO eventDTO = new EventDTO(event.getId(), event.getName(),
				event.getStart(), event.getEnd(), event.getCommunity(),
				event.getEventOwner());
		return eventDTO;
	}

	public static List<EventDTO> eventListToEventDTOListMapper(
			List<Event> events) {
		List<EventDTO> eventDTOs = new ArrayList<>();
		for (Event event : events) {
			eventDTOs.add(new EventDTO(event.getId(), event.getName(),
					event.getStart(), event.getEnd(), event.getCommunity(),
					event.getEventOwner()));
		}
		return eventDTOs;
	}

	public static Event eventDTOToEventMapper(EventDTO eventDTO) {
		Event event = new Event(eventDTO.getId(), eventDTO.getName(),
				eventDTO.getStart(), eventDTO.getEnd(), eventDTO.getCommunity(),
				eventDTO.getEventOwner());
		return event;
	}

	public static List<Event> eventDTOListToEventListMapper(
			List<EventDTO> eventDTOs) {
		List<Event> events = new ArrayList<>();
		for (EventDTO eventDTO : eventDTOs) {
			events.add(new Event(eventDTO.getId(), eventDTO.getName(),
					eventDTO.getStart(), eventDTO.getEnd(),
					eventDTO.getCommunity(), eventDTO.getEventOwner()));
		}
		return events;
	}
}
