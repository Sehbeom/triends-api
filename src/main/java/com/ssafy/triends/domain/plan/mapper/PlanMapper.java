package com.ssafy.triends.domain.plan.mapper;

import com.ssafy.triends.domain.plan.model.CourseDto;
import com.ssafy.triends.domain.plan.model.DayDto;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {
	List<PlanDto> getMyPlanList(int userId) throws SQLException;

	PlanDto getPlanDetail(int planId) throws SQLException;

	void delete(int planId) throws SQLException;

	int createPlan(Map<String, Object> planAndCourse) throws SQLException;

	int insertMembers(Map<String, Object> planAndUserIds) throws SQLException;

	int createDay(DayDto dayDto) throws SQLException;

	int createCourse(CourseDto courseDto) throws SQLException;

	int acceptMember(Map<String, Object> userAndPlanId) throws SQLException;
}
