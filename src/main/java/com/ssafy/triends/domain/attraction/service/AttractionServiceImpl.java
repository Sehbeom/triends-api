package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.mapper.AttractionMapper;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import java.util.List;
import java.util.Map;

import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import com.ssafy.triends.global.util.PreferenceSimilarityCaculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;
	private Logger logger = LoggerFactory.getLogger(AttractionServiceImpl.class);
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
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
	public List<AttractionDto> getAttractionsOrderByRates() throws Exception {
		return attractionMapper.getAttractionsOrderByRates();
	}

	@Override
	public UserPreferenceDto getRecommendAttractions(int userId) throws Exception {
		UserPreferenceDto userPreferenceDto = attractionMapper.getOneUserPreferences(userId);
		List<UserPreferenceDto> others = attractionMapper.getAllOtherUsersPreferences(userId);
		logger.debug("others : {}", others);
		Map<UserPreferenceDto, Double> similarities = PreferenceSimilarityCaculator.calculateOneWithOthers(userPreferenceDto, others);

		for (Map.Entry<UserPreferenceDto, Double> s : similarities.entrySet()) {
			logger.debug("user : {}, similarity : {}", s.getKey().getUserId(), s.getValue());
		}

		return userPreferenceDto;
	}

}
