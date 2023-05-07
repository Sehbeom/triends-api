package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.CourseDto;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	public void delete(int planId) throws Exception {
		planMapper.delete(planId);
	}

	@Override
	public int createPlanAndCourses(Map<String, Object> planAndCourse) throws Exception {
		PlanDto planDto = makePlanDtoWithBasicInfo((Map<String, Object>) planAndCourse.get("plan"));
		List<CourseDto> courseDtos = makeCourseDtosWithBasicInfo((List<Map<String, Object>>) planAndCourse.get("course"));

		Map<String, Object> mapperParameter = makeMapperParameter(planDto, courseDtos);
		planMapper.createPlan(mapperParameter);
		return planMapper.createCourses(mapperParameter);
	}

	@Override
	public int acceptMember(Map<String, Object> userAndPlanId) throws Exception {
		return planMapper.acceptMember(userAndPlanId);
	}

	private PlanDto makePlanDtoWithBasicInfo(Map<String, Object> plan) {
		PlanDto planDto = new PlanDto();
		planDto.setTitle((String)plan.get("title"));
		planDto.setStartDate((String)plan.get("startDate"));
		planDto.setEndDate((String)plan.get("endDate"));

		return planDto;
	}

	private List<CourseDto> makeCourseDtosWithBasicInfo(List<Map<String, Object>> courses) {
		List<CourseDto> courseDtos = new ArrayList<>();

		for (Map<String, Object> course : courses) {
			CourseDto courseDto = new CourseDto();
			courseDto.setDays(Integer.parseInt((String) course.get("days")));
			courseDto.setStartTime(Integer.parseInt((String) course.get("startTime")));
			courseDto.setEndTime(Integer.parseInt((String) course.get("endTime")));
			courseDto.setContentId(Integer.parseInt((String) course.get("contentId")));
			courseDtos.add(courseDto);
		}

		return courseDtos;
	}

	private Map<String, Object> makeMapperParameter(PlanDto planDto, List<CourseDto> courseDtos) {
		Map<String, Object> mapperParameter = new HashMap<>();
		mapperParameter.put("plan", planDto);
		mapperParameter.put("courses", courseDtos);

		return mapperParameter;
	}
}
