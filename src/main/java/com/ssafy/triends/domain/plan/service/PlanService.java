package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.model.PlanDto;

import java.util.List;
import java.util.Map;

public interface PlanService {
	List<PlanDto> getMyPlanList(int userId) throws Exception;

	PlanDto getPlanWithCourse(int planId) throws Exception;

	void inviteMember(Map<String, String> userAndPlanId) throws Exception;

	void delete(int planId) throws Exception;
}
