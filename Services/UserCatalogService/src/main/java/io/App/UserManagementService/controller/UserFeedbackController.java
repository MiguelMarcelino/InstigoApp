package io.App.UserManagementService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.UserManagementService.dto.FeedbackDataDTO;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.userComponent.FeedbackCatalog;

@RestController
@RequestMapping("/userFeedbackApi")
public class UserFeedbackController {

	@Autowired
	private FeedbackCatalog fC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@PostMapping(path = "receiveFeedback", consumes = { "application/json" })
	public ResponseEntity<String> receiveFeedback(
			@RequestBody String feedbackJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		FeedbackDataDTO feedbackDataDTO = null;

		try {
			feedbackDataDTO = objectMapper.readValue(feedbackJSON, FeedbackDataDTO.class);

			this.fC.storeFeedback(feedbackDataDTO.getUserName(),
					feedbackDataDTO.getFeedback());
		} catch (JsonParseException | JsonMappingException
				| InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully added new Feedback");
		return new ResponseEntity<>("Successfully added new Feedback",
				HttpStatus.OK);
	}
}
