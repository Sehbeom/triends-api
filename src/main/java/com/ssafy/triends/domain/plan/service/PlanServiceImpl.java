package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.json.simple.JSONArray;
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
	public int createPlan(String startDate, String endDate, String title, String thumbnail, JSONArray members,
			JSONArray course) throws Exception {
		return 0;
	}

	@Override
	public int updatePlan(int planId, String startDate, String endDate, String title, String thumbnail,
			JSONArray members, JSONArray course) throws Exception {
		return 0;
	}

	@Override
	public List<PlanDto> getPlans(int userId) throws Exception {
		return null;
	}

	@Override
	public PlanDto getPlan(int planId) throws Exception {
		return null;
	}

	@Override
	public int updatePlanMembers(int planId, int userId, String userName) throws Exception {
		return 0;
	}

	@Override
	public int deletePlan(int userId, int planId) throws Exception {
		return 0;
	}

	@Override
	public int duplicatePlan(int userId, String name, int planId) throws Exception {
		return 0;
	}
	

}
