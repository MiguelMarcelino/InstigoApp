package io.App.UserManagementService.controller;

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

import io.App.UserManagementService.dto.FeedbackDataDTO;
import io.App.UserManagementService.dto.FeedbackListWrapper;
import io.App.UserManagementService.dto.Pair;
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
	
	@GetMapping(path = "userFeedbacks/{uID}")
	public ResponseEntity<Pair<String, FeedbackListWrapper>> getAllFeedback(
			@PathVariable("uID") String uID) {
		FeedbackListWrapper fLW = null;

		try {
			fLW = new FeedbackListWrapper(fC.getAllFeedback());
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully added new Feedback");
		return new ResponseEntity<>(new Pair<>("Successfully added new Feedback", fLW),
				HttpStatus.OK);
	}
}
