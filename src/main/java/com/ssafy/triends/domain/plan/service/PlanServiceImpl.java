package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public void inviteMember(Map<String, String> userAndPlanId) throws Exception {
		Map<String, Object> typeCastedUserAndPlanId = new HashMap<>();
		typeCastedUserAndPlanId.put("userId", Integer.parseInt(userAndPlanId.get("userId")));
		typeCastedUserAndPlanId.put("sender", Integer.parseInt(userAndPlanId.get("sender")));
		typeCastedUserAndPlanId.put("planId", Integer.parseInt(userAndPlanId.get("planId")));

		planMapper.inviteMember(typeCastedUserAndPlanId);
	}

	@Override
	public void delete(int planId) throws Exception {
		//TODO: notify 테이블 내 planId에 해당하는 알림 삭제 (notify_type='plan' and additional_info=#{planId})
		planMapper.delete(planId);
	}


}
