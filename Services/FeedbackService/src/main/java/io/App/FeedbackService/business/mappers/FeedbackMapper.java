package io.App.FeedbackService.business.mappers;

import java.util.ArrayList;
import java.util.List;

import io.App.FeedbackService.business.Feedback;
import io.App.FeedbackService.business.dto.FeedbackDTO;

public class FeedbackMapper {

	public static FeedbackDTO feedbackToFeedbackDTOMapper(Feedback feedback) {
		FeedbackDTO feedbackDTO = new FeedbackDTO(feedback.getId(),
				feedback.getUsername(), feedback.getDatePublished(),
				feedback.getFeedbackContent());
		return feedbackDTO;
	}

	public static List<FeedbackDTO> feedbackListToFeedbackDTOListMapper(
			List<Feedback> feedbacks) {
		List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
		for (Feedback feedback : feedbacks) {
			feedbackDTOs.add(new FeedbackDTO(feedback.getId(),
					feedback.getUsername(), feedback.getDatePublished(),
					feedback.getFeedbackContent()));
		}
		return feedbackDTOs;
	}

	public static Feedback feedbackDTOToFeedbackMapper(
			FeedbackDTO feedbackDTO) {
		Feedback feedback = new Feedback(feedbackDTO.getId(),
				feedbackDTO.getUserName(), feedbackDTO.getDatePublished(),
				feedbackDTO.getFeedbackContent());
		return feedback;
	}

	public static List<Feedback> feedbackDTOListToFeedbackListMapper(
			List<FeedbackDTO> feedbackDTOs) {
		List<Feedback> feedbacks = new ArrayList<>();
		for (FeedbackDTO feedbackDTO : feedbackDTOs) {
			feedbacks.add(new Feedback(feedbackDTO.getId(),
					feedbackDTO.getUserName(), feedbackDTO.getDatePublished(),
					feedbackDTO.getFeedbackContent()));
		}
		return feedbacks;
	}
}
