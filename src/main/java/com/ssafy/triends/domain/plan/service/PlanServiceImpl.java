package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
	private PlanMapper planMapper;

	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}


	@Override
	public List<PlanDto> getMyPlanList(int userId) throws Exception {
		return planMapper.getMyPlanList(userId);
	}

	@Override
	public PlanDto getPlanWithCourse(int planId) throws Exception {
		return planMapper.getPlanWithCourse(planId);
	}
}
