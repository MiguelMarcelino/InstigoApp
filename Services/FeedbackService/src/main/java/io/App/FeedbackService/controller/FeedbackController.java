package io.App.FeedbackService.controller;

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

import io.App.FeedbackService.business.catalogs.FeedbackCatalog;
import io.App.FeedbackService.business.dto.FeedbackDTO;
import io.App.FeedbackService.business.dto.FeedbackListWrapper;
import io.App.FeedbackService.business.dto.Pair;
import io.App.FeedbackService.business.exceptions.InternalAppException;
import io.App.FeedbackService.business.mappers.FeedbackMapper;

@RestController
@RequestMapping("/feedbackApi")
public class FeedbackController {

	@Autowired
	private FeedbackCatalog fC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@PostMapping(path = "receiveFeedback", consumes = { "application/json" })
	public ResponseEntity<String> receiveFeedback(
			@RequestBody String feedbackJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		FeedbackDTO feedbackDTO = null;

		try {
			feedbackDTO = objectMapper.readValue(feedbackJSON,
					FeedbackDTO.class);

			this.fC.storeFeedback(feedbackDTO.getUserName(),
					feedbackDTO.getDatePublished(),
					feedbackDTO.getFeedbackContent());
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
			fLW = new FeedbackListWrapper(FeedbackMapper
					.feedbackListToFeedbackDTOListMapper(fC.getAllFeedback()));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully added new Feedback");
		return new ResponseEntity<>(
				new Pair<>("Successfully added new Feedback", fLW),
				HttpStatus.OK);
	}
}
