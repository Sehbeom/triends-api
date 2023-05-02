package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.model.PlanDto;
import org.json.simple.JSONArray;

import java.util.List;

public interface PlanService {
	int createPlan(String startDate, String endDate, String title, String thumbnail, JSONArray members, JSONArray course) throws Exception;

	int updatePlan(int planId, String startDate, String endDate, String title, String thumbnail, JSONArray members, JSONArray course) throws Exception;

	List<PlanDto> getPlans(int userId) throws Exception;
	
	PlanDto getPlan(int planId) throws Exception;

	int updatePlanMembers(int planId, int userId, String userName) throws Exception;

	int deletePlan(int userId, int planId) throws Exception;
	
	int duplicatePlan(int userId, String name, int planId) throws Exception;
}
