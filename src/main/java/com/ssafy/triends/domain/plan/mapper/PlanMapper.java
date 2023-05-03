package com.ssafy.triends.domain.plan.mapper;

import com.ssafy.triends.domain.plan.model.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {
	List<PlanDto> getMyPlanList(int userId) throws SQLException;

	PlanDto getPlanWithCourse(int planId) throws SQLException;

	void inviteMember(Map<String, Object> userAndPlanId) throws SQLException;

	void delete(int planId) throws SQLException;
}
