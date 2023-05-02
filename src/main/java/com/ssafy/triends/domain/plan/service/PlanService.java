package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.model.PlanDto;

import java.util.List;

public interface PlanService {
	List<PlanDto> getMyPlanList(int userId) throws Exception;

	PlanDto getPlanWithCourse(int planId) throws Exception;
}
