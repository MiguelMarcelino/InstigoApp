package io.App.EventService.EventComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.UserEventDatabaseManagement;
import io.App.EventService.dto.EventDTO;
import io.App.EventService.exceptions.InternalAppException;

@SpringBootApplication
public class UserEventCatalog {

	private UserEventDatabaseManagement uEDM;

	public UserEventCatalog() {
		this.uEDM = new UserEventDatabaseManagement();
	}

	public List<EventDTO> eventsFromSubbedCommunities(int uID) throws NumberFormatException, InternalAppException {
		return uEDM.eventsFromSubCommunities(uID);
	}

	public List<EventDTO> eventsCreatedByUser(String userName) throws InternalAppException {
		return uEDM.userCreatedEvents(userName);
	}

}
