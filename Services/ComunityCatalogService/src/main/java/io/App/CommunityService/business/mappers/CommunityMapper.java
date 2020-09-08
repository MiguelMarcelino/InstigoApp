package io.App.CommunityService.business.mappers;

import java.util.ArrayList;
import java.util.List;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.facade.dto.CommunityDTO;

public class CommunityMapper {

	public static CommunityDTO communityToCommunityDTOMapper(
			Community community) {
		CommunityDTO communityDTO = new CommunityDTO(community.getId(),
				community.getName(), community.getDescription(),
				UserMapper.userToUserDTOMapper(community.getCommunityOwner()));
		return communityDTO;
	}

	public static List<CommunityDTO> communityListToCommunityDTOList(
			List<Community> communities) {
		List<CommunityDTO> communityDTOs = new ArrayList<>();
		for (Community c : communities) {
			communityDTOs.add(new CommunityDTO(c.getId(), c.getName(),
					c.getDescription(),
					UserMapper.userToUserDTOMapper(c.getCommunityOwner())));
		}
		return communityDTOs;
	}

	public static Community communityDTOToCommunityMapper(
			CommunityDTO communityDTO) {
		Community community = new Community(communityDTO.getId(),
				communityDTO.getName(), communityDTO.getDescription(),
				UserMapper
						.userDTOToUserMapper(communityDTO.getCommunityOwner()));
		return community;
	}

	public static List<Community> communityDTOListToCommunityList(
			List<CommunityDTO> communityDTOs) {
		List<Community> communities = new ArrayList<>();
		for (CommunityDTO c : communityDTOs) {
			communityDTOs.add(new CommunityDTO(c.getId(), c.getName(),
					c.getDescription(), c.getCommunityOwner()));
		}
		return communities;
	}
}
