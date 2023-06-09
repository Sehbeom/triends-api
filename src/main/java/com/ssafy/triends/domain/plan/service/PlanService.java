package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.model.DayDto;
import com.ssafy.triends.domain.plan.model.PlanDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
	List<PlanDto> getMyPlanList(int userId) throws Exception;

	PlanDto getPlanDetail(int planId) throws Exception;

	void delete(int planId) throws Exception;

	int createPlan(Map<String, Object> planAndCourse, int userId) throws Exception;

	int updatePlan(Map<String, Object> planAndCourse, int userId, int planId) throws Exception;

	void acceptMember(Map<String, Object> userAndNotificationAndPlanId) throws Exception;

	List<DayDto> getRecommendPlans(Map<String, Object> latLngInfo) throws Exception;
}
