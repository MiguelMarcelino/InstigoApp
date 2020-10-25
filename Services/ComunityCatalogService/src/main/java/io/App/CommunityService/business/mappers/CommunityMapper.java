package io.App.CommunityService.business.mappers;

import java.util.ArrayList;
import java.util.List;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.dto.CommunityDTO;

public class CommunityMapper {

	public static CommunityDTO communityToCommunityDTOMapper(
			Community community) {
		CommunityDTO communityDTO = new CommunityDTO(community.getId(),
				community.getName(), community.getDescription(),
				community.getCommunityOwnerId());
		return communityDTO;
	}

	public static List<CommunityDTO> communityListToCommunityDTOList(
			List<Community> communities) {
		List<CommunityDTO> communityDTOs = new ArrayList<>();
		for (Community c : communities) {
			communityDTOs.add(new CommunityDTO(c.getId(), c.getName(),
					c.getDescription(), c.getCommunityOwnerId()));
		}
		return communityDTOs;
	}

	public static Community communityDTOToCommunityMapper(
			CommunityDTO communityDTO) {
		Community community = new Community(communityDTO.getId(),
				communityDTO.getName(), communityDTO.getDescription(),
				communityDTO.getCommunityOwnerID());
		return community;
	}

	public static List<Community> communityDTOListToCommunityList(
			List<CommunityDTO> communityDTOs) {
		List<Community> communities = new ArrayList<>();
		for (CommunityDTO c : communityDTOs) {
			communityDTOs.add(new CommunityDTO(c.getId(), c.getName(),
					c.getDescription()));
		}
		return communities;
	}
}
