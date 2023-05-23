package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.CourseDto;
import com.ssafy.triends.domain.plan.model.DayDto;
import com.ssafy.triends.domain.plan.model.PlanDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    private Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
    private PlanMapper planMapper;
    private NotificationMapper notificationMapper;

    public PlanServiceImpl(PlanMapper planMapper, NotificationMapper notificationMapper) {
        super();
        this.planMapper = planMapper;
        this.notificationMapper = notificationMapper;
    }


    @Override
    public List<PlanDto> getMyPlanList(int userId) throws Exception {
        return planMapper.getMyPlanList(userId);
    }

    @Override
    public PlanDto getPlanDetail(int planId) throws Exception {
        return planMapper.getPlanDetail(planId);
    }

    @Override
    public void delete(int planId) throws Exception {
        notificationMapper.deleteNotificationByPlanId(planId);
        planMapper.delete(planId);
    }

    @Override
    public int createPlan(Map<String, Object> planInfo, int userId) throws Exception {
        PlanDto planDto = makePlanDto((Map<String, Object>) planInfo.get("planInfo"));
        List<Integer> contentIds = makeContentIds(
                (List<Map<String, Object>>) planInfo.get("courseInfo"));
        Map<String, Object> planInsertParameter = makePlanInsertParameter(planDto, contentIds);
        // TODO : plan 테이블 삽입
        planMapper.createPlan(planInsertParameter);

        inviteMembers(planDto, (List<String>) planInfo.get("memberInfo"), userId);
        insertDaysAndCourses(planDto, (List<Map<String, Object>>) planInfo.get("courseInfo"));

        return planDto.getPlanId();
    }

    private void inviteMembers(PlanDto planDto, List<String> memberInfo, int userId)
            throws Exception {
        Map<String, Object> memberInviteParameter = makeMemberInviteParameter(planDto, memberInfo,
                userId);
        Map<String, Object> acceptMemberParameter = makeAcceptMemberParameter(planDto.getPlanId(),
                userId);

        planMapper.acceptMember(acceptMemberParameter);
        notificationMapper.sendPlanMemberInvitations(memberInviteParameter);
    }

    private Map<String, Object> makeMemberInviteParameter(PlanDto planDto, List<String> memberInfo,
            int userId) {
        Map<String, Object> memberInsertParameter = new HashMap<>();
        memberInsertParameter.put("planId", planDto.getPlanId());
        memberInsertParameter.put("receiverIds", memberInfo.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        memberInsertParameter.put("senderId", userId);

        return memberInsertParameter;
    }

    private Map<String, Object> makeAcceptMemberParameter(int planId, int userId) {
        Map<String, Object> acceptMemberParameter = new HashMap<>();
        acceptMemberParameter.put("planId", planId);
        acceptMemberParameter.put("userId", userId);

        return acceptMemberParameter;
    }

    @Override
    public void acceptMember(Map<String, Object> userAndNotificationAndPlanId)
            throws Exception {
        planMapper.acceptMember(userAndNotificationAndPlanId);
        notificationMapper.deleteOneNotification(
                Integer.parseInt((String) userAndNotificationAndPlanId.get("notificationId")));
    }

    @Override
    public List<DayDto> getRecommendPlans(Map<String, Object> latLngInfo) throws Exception {
        return planMapper.getRecommendPlans(latLngInfo);
    }

    private PlanDto makePlanDto(Map<String, Object> plan) {
        PlanDto planDto = new PlanDto();
        planDto.setTitle((String) plan.get("title"));
        planDto.setStartDate((String) plan.get("startDate"));
        planDto.setEndDate((String) plan.get("endDate"));

        return planDto;
    }

    private List<Integer> makeContentIds(List<Map<String, Object>> courseInfo) {
        List<Integer> contentIds = new ArrayList<>();

        for (Map<String, Object> oneDay : courseInfo) {
            for (Map<String, Object> oneCourse : (List<Map<String, Object>>) oneDay.get(
                    "courses")) {
                contentIds.add(Integer.parseInt((String) oneCourse.get("contentId")));
            }
        }

        return contentIds;
    }

    private Map<String, Object> makePlanInsertParameter(PlanDto planDto, List<Integer> contentIds) {
        Map<String, Object> planInsertParameter = new HashMap<>();
        planInsertParameter.put("plan", planDto);
        planInsertParameter.put("contentIds", contentIds);

        return planInsertParameter;
    }

    private void insertDaysAndCourses(PlanDto planDto, List<Map<String, Object>> courseInfo)
            throws Exception {
        for (Map<String, Object> oneDay : courseInfo) {
            DayDto dayDto = insertDayInfoToDays(planDto, oneDay);
            logger.debug("days Id : {}", dayDto.getDaysId());

            for (Map<String, Object> oneCourse : (List<Map<String, Object>>) oneDay.get(
                    "courses")) {
                insertCourseInfoToCourse(dayDto, oneCourse);
            }
        }
    }

    private DayDto insertDayInfoToDays(PlanDto planDto, Map<String, Object> oneDay)
            throws Exception {
        DayDto dayDto = new DayDto();
        dayDto.setPlanId(planDto.getPlanId());
        dayDto.setDayInfo(Integer.parseInt((String) oneDay.get("day")));

        // TODO : days 테이블 삽입
        planMapper.createDay(dayDto);

        return dayDto;
    }

    private void insertCourseInfoToCourse(DayDto dayDto, Map<String, Object> oneCourse)
            throws Exception {
        CourseDto courseDto = new CourseDto();
        courseDto.setDaysId(dayDto.getDaysId());
        courseDto.setContentId(Integer.parseInt((String) oneCourse.get("contentId")));
        courseDto.setStartTime(Integer.parseInt((String) oneCourse.get("startTime")));
        courseDto.setEndTime(Integer.parseInt((String) oneCourse.get("endTime")));
        // TODO : course 테이블 삽입
        planMapper.createCourse(courseDto);
    }


}
