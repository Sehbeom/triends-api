package com.ssafy.triends.domain.attraction.service;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import org.json.simple.JSONArray;

import java.util.List;

public interface AttractionService {
	AttractionDto getInfo(int contentId) throws Exception;
	List<AttractionDto> searchByLatLng(double swLat, double swLng, double neLat, double neLng, List<Integer> types, int page) throws Exception;
	List<AttractionDto> searchByAreaCode(int area, int sigungu, List<Integer> types, int page) throws Exception;
	List<AttractionDto> searchByKeyword(int area, int sigungu, String keyword, List<Integer> types, int page) throws Exception;
	JSONArray sido() throws Exception;
	JSONArray gungu(int sido) throws Exception;
}
