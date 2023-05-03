package com.ssafy.triends.domain.notification.mapper;

import com.ssafy.triends.domain.notification.model.NotificationDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface NotificationMapper {
	int sendPlanMemberInvitation(Map<String, Object> userAndSenderAndPlanId) throws SQLException;
	int sendFriendRequest(Map<String, Object> userAndSenderId) throws SQLException;
	List<NotificationDto> getAllNotifications(int userId) throws SQLException;
	int deleteOneNotification(int notificationId) throws SQLException;
}
