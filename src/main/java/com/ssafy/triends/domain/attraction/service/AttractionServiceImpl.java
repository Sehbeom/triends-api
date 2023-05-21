package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.mapper.AttractionMapper;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import com.ssafy.triends.domain.user.mapper.UserMapper;
import java.util.List;
import java.util.Map;

import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import com.ssafy.triends.global.util.PreferenceSimilarityCaculator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;
	private UserMapper userMapper;
	private Logger logger = LoggerFactory.getLogger(AttractionServiceImpl.class);
	
	public AttractionServiceImpl(AttractionMapper attractionMapper, UserMapper userMapper) {
		super();
		this.attractionMapper = attractionMapper;
		this.userMapper = userMapper;
	}


	@Override
	public AttractionDto getAttractionDetail(int attractionId) throws Exception {
		return attractionMapper.getAttractionDetail(attractionId);
	}

	@Override
	public List<AttractionDto> searchAttractions(SearchDto searchDto) throws Exception {
		return attractionMapper.searchAttractions(searchDto);
	}

	@Override
	public List<AttractionDto> getAttractionsOrderByRates(Map<String, Object> latLngInfo) throws Exception {
		return attractionMapper.getAttractionsOrderByRates(latLngInfo);
	}

	@Override
	public List<AttractionDto> getRecommendAttractions(int userId) throws Exception {
		UserPreferenceDto userPreferenceDto = userMapper.getOneUserPreferences(userId);
		List<UserPreferenceDto> others = userMapper.getAllOtherUsersPreferences(userId);
		logger.debug("others : {}", others);
		Map<UserPreferenceDto, Double> similarities = PreferenceSimilarityCaculator.calculateOneWithOthers(userPreferenceDto, others);

		for (Map.Entry<UserPreferenceDto, Double> s : similarities.entrySet()) {
			logger.debug("user : {}, similarity : {}", s.getKey().getUserId(), s.getValue());
		}
		List<Integer> similarUserIds = similarities.keySet().stream()
				.map(u -> u.getUserId())
				.collect(Collectors.toList())
				.subList(0, 6);

		logger.debug("userIds : {}", similarUserIds);

		List<AttractionDto> recommendAttractions = attractionMapper.getRecommendAttractions(similarUserIds);

		return recommendAttractions;
	}

}
