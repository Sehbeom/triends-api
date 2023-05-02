package com.ssafy.triends.domain.attraction.mapper;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AttractionMapper {
	AttractionDto getInfo(int contentId) throws SQLException;
	List<AttractionDto> searchByLatLng(double swLat, double swLng, double neLat, double neLng, List<Integer> types, int page) throws SQLException;
	List<AttractionDto> searchByAreaCode(int area, int sigungu, List<Integer> types, int page) throws SQLException;
	List<AttractionDto> searchByKeyword(int area, int sigungu, String keyword, List<Integer> types, int page) throws SQLException;
	JSONArray sido() throws SQLException;
	JSONArray gungu(int sido) throws SQLException;
}
