package com.ssafy.triends.domain.notification.service;

import com.ssafy.triends.domain.notification.model.NotificationDto;
import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface NotificationService {
	int sendPlanMemberInvitation(Map<String, Object> receiverAndPlanId, int senderId) throws Exception;
	int sendFriendRequest(int receiverId, int senderId) throws Exception;
	List<NotificationDto> getAllNotifications(int userId) throws Exception;
	int deleteOneNotification(int notificationId) throws Exception;
}
