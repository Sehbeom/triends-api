package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.model.DayDto;
import com.ssafy.triends.domain.plan.model.PlanDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PlanService {
	List<PlanDto> getMyPlanList(int userId) throws Exception;

	PlanDto getPlanDetail(int planId) throws Exception;

	void delete(int planId) throws Exception;

	void createPlan(Map<String, Object> planAndCourse) throws Exception;

	int acceptMember(Map<String, Object> userAndPlanId) throws Exception;

	List<DayDto> getRecommendPlans(Map<String, Object> latLngInfo) throws Exception;
}
