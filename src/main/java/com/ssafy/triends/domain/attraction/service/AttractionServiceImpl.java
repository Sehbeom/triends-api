package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.mapper.AttractionMapper;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {
	private AttractionMapper attractionInfoMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionInfoMapper) {
		super();
		this.attractionInfoMapper = attractionInfoMapper;
	}

	@Override
	public AttractionDto getInfo(int contentId) throws Exception {
		return null;
	}

	@Override
	public List<AttractionDto> searchByLatLng(double swLat, double swLng, double neLat, double neLng,
			List<Integer> types, int page) throws Exception {
		return null;
	}

	@Override
	public List<AttractionDto> searchByAreaCode(int area, int sigungu, List<Integer> types, int page)
			throws Exception {
		return null;
	}

	@Override
	public List<AttractionDto> searchByKeyword(int area, int sigungu, String keyword, List<Integer> types, int page)
			throws Exception {
		return null;
	}

	@Override
	public JSONArray sido() throws Exception {
		return null;
	}

	@Override
	public JSONArray gungu(int sido) throws Exception {
		return null;
	}

	
}
