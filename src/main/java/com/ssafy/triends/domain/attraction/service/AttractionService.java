package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import java.util.List;

public interface AttractionService {
	AttractionDto getAttractionDetail(int attractionId) throws Exception;
	List<AttractionDto> searchAttractions(SearchDto searchDto) throws Exception;
}
