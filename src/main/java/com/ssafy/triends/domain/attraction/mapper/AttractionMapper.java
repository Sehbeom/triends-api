package com.ssafy.triends.domain.attraction.mapper;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import com.ssafy.triends.domain.attraction.model.SearchDto;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionMapper {
	AttractionDto getAttractionDetail(int attractionId) throws SQLException;
	List<AttractionDto> searchAttractions(SearchDto searchDto) throws SQLException;
}
