package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;

import java.sql.SQLException;
import java.util.List;

public interface AttractionService {
	AttractionDto getAttractionDetail(int attractionId) throws Exception;
	List<AttractionDto> searchAttractions(SearchDto searchDto) throws Exception;
	List<AttractionDto> getAttractionsOrderByRates() throws Exception;
//	List<AttractionDto> getRecommendAttractions(int userId) throws Exception;
	UserPreferenceDto getRecommendAttractions(int userId) throws Exception;
//	List<UserPreferenceDto> getRecommendAttractions(int userId) throws Exception;
}
