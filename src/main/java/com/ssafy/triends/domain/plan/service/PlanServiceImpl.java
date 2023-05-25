package com.ssafy.triends.domain.plan.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.plan.mapper.PlanMapper;
import com.ssafy.triends.domain.plan.model.CourseDto;
import com.ssafy.triends.domain.plan.model.DayDto;
import com.ssafy.triends.domain.plan.model.PlanDto;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        inviteMembers(planDto, (List<Integer>) planInfo.get("memberInfo"), userId);
        insertDaysAndCourses(planDto, (List<Map<String, Object>>) planInfo.get("courseInfo"));

        return planDto.getPlanId();
    }

    @Override
    public int updatePlan(Map<String, Object> planAndCourse, int userId) throws Exception {
        Map<String, Object> planInfo = (Map<String, Object>) planAndCourse.get("planInfo");

        PlanDto planDto = makePlanDto(planInfo);
        List<Integer> contentIds = makeContentIds(
                (List<Map<String, Object>>) planAndCourse.get("courseInfo"));
        Map<String, Object> planInsertParameter = makePlanInsertParameter(planDto, contentIds);
        planMapper.createPlan(planInsertParameter);

        insertMembers(planDto, (List<Map<String, Object>>) planAndCourse.get("memberInfo"));
        insertDaysAndCourses(planDto, (List<Map<String, Object>>) planAndCourse.get("courseInfo"));

        planMapper.delete((Integer) planInfo.get("planId"));
        notificationMapper.deleteNotificationByPlanId((Integer) planInfo.get("planId"));

        return planDto.getPlanId();
    }

    @Override
    public void acceptMember(Map<String, Object> userAndNotificationAndPlanId)
            throws Exception {
        planMapper.acceptMember(userAndNotificationAndPlanId);
        notificationMapper.deleteOneNotification((Integer) userAndNotificationAndPlanId.get("notificationId"));
    }

    @Override
    public List<DayDto> getRecommendPlans(Map<String, Object> latLngInfo) throws Exception {
        return planMapper.getRecommendPlans(latLngInfo);
    }

    private void insertMembers(PlanDto planDto, List<Map<String, Object>> memberInfo)
            throws Exception {
        Map<String, Object> insertMembersParameter = new HashMap<>();
        insertMembersParameter.put("planId", planDto.getPlanId());

        List<Integer> memberIds = new ArrayList<>();
        for (Map<String, Object> member : memberInfo) {
            memberIds.add((Integer) member.get("userId"));
        }

        insertMembersParameter.put("memberIds", memberIds);

        planMapper.insertMembers(insertMembersParameter);
    }

    private void inviteMembers(PlanDto planDto, List<Integer> memberInfo, int userId)
            throws Exception {
        Map<String, Object> memberInviteParameter = makeMemberInviteParameter(planDto, memberInfo,
                userId);
        Map<String, Object> acceptMemberParameter = makeAcceptMemberParameter(planDto.getPlanId(),
                userId);

        planMapper.acceptMember(acceptMemberParameter);
        notificationMapper.sendPlanMemberInvitations(memberInviteParameter);
    }

    private Map<String, Object> makeMemberInviteParameter(PlanDto planDto, List<Integer> memberInfo,
            int userId) {
        Map<String, Object> memberInsertParameter = new HashMap<>();
        memberInsertParameter.put("planId", planDto.getPlanId());
        List<Integer> members = new ArrayList<>();
        for (int memberId : memberInfo) {
            if (memberId != userId) {
                members.add(memberId);
            }
        }
        memberInsertParameter.put("receiverIds", members);
        memberInsertParameter.put("senderId", userId);

        return memberInsertParameter;
    }

    private Map<String, Object> makeAcceptMemberParameter(int planId, int userId) {
        Map<String, Object> acceptMemberParameter = new HashMap<>();
        acceptMemberParameter.put("planId", planId);
        acceptMemberParameter.put("userId", userId);

        return acceptMemberParameter;
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
                contentIds.add((Integer) oneCourse.get("contentId"));
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
        dayDto.setDayInfo((Integer) oneDay.get("dayInfo"));

        // TODO : days 테이블 삽입
        planMapper.createDay(dayDto);

        return dayDto;
    }

    private void insertCourseInfoToCourse(DayDto dayDto, Map<String, Object> oneCourse)
            throws Exception {
        CourseDto courseDto = new CourseDto();
        courseDto.setDaysId(dayDto.getDaysId());
        courseDto.setContentId((Integer) oneCourse.get("contentId"));
        if (oneCourse.get("startTime") != null && oneCourse.get("endTime") != null) {
            courseDto.setStartTime((Integer) oneCourse.get("startTime"));
            courseDto.setEndTime((Integer) oneCourse.get("endTime"));
        }
        // TODO : course 테이블 삽입
        planMapper.createCourse(courseDto);
    }

}
