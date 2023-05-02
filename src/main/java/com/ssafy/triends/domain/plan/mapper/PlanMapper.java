package com.ssafy.triends.domain.plan.mapper;

import com.ssafy.triends.domain.plan.model.PlanDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlanMapper {
	int createPlan(String startDate, String endDate, String title, String thumbnail, JSONArray members, JSONArray course) throws SQLException;

	int updatePlan(int planId, String startDate, String endDate, String title, String thumbnail, JSONArray members, JSONArray course) throws SQLException;
	
	List<PlanDto> getPlans(int userId) throws SQLException, ParseException;

	PlanDto getPlan(int planId) throws SQLException, ParseException;

	int updatePlanMembers(int planId, int userId, String userName) throws SQLException;

	int deletePlan(int userId, int planId) throws SQLException;
	
	int duplicatePlan(int userId, String name, int planId) throws SQLException;
}
