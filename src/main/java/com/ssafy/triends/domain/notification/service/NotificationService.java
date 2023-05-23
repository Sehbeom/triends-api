package com.ssafy.triends.domain.notification.service;

import com.ssafy.triends.domain.notification.model.NotificationDto;
import java.util.List;
import java.util.Map;

public interface NotificationService {
	int sendPlanMemberInvitation(Map<String, Object> receiverAndPlanAndUserId) throws Exception;
	int sendFriendRequest(Map<String, Object> receiverAndUserId) throws Exception;
	List<NotificationDto> getAllNotifications(int userId) throws Exception;
	int deleteOneNotification(int notificationId) throws Exception;
}
