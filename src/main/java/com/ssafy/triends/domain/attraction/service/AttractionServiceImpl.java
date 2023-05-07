package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.mapper.AttractionMapper;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionMapper;
	
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
}
