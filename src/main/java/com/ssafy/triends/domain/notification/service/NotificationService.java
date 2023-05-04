package com.ssafy.triends.domain.notification.service;

import com.ssafy.triends.domain.notification.model.NotificationDto;
import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface NotificationService {
	int sendPlanMemberInvitation(Map<String, String> userAndSenderAndPlanId) throws Exception;
	int sendFriendRequest(Map<String, String> userAndSenderId) throws Exception;
	List<NotificationDto> getAllNotifications(int userId) throws Exception;
	int deleteOneNotification(int notificationId) throws Exception;
}
