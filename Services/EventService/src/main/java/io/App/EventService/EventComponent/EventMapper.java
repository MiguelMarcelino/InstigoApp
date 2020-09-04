package io.App.EventService.EventComponent;

import java.util.List;
import java.util.stream.Collectors;

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
		List<EventDTO> eventDTOs = events.stream()
				.map(event -> new EventDTO(event.getId(), event.getName(),
						event.getStart(), event.getEnd(), event.getCommunity(),
						event.getEventOwner()))
				.collect(Collectors.toList());
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
		List<Event> events = eventDTOs.stream()
				.map(eventDTO -> new Event(eventDTO.getId(), eventDTO.getName(),
						eventDTO.getStart(), eventDTO.getEnd(),
						eventDTO.getCommunity(), eventDTO.getEventOwner()))
				.collect(Collectors.toList());
		return events;
	}
}
