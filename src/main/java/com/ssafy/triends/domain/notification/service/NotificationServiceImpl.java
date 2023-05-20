package com.ssafy.triends.domain.notification.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.notification.model.NotificationDto;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	private NotificationMapper notificationMapper;

	public NotificationServiceImpl(NotificationMapper notificationMapper) {
		super();
		this.notificationMapper = notificationMapper;
	}


	@Override
	public int sendPlanMemberInvitation(Map<String, Object> receiverAndPlanId, int senderId) throws Exception {
		receiverAndPlanId.put("senderId", senderId);
		return notificationMapper.sendOnePlanMemberInvitation(receiverAndPlanId);
	}

	@Override
	public int sendFriendRequest(int receiverId, int senderId) throws Exception {
		Map<String, Object> receiverAndSenderId = new HashMap<>();
		receiverAndSenderId.put("receiverId", receiverId);
		receiverAndSenderId.put("senderId", senderId);
		return notificationMapper.sendFriendRequest(receiverAndSenderId);
	}

	@Override
	public List<NotificationDto> getAllNotifications(int userId) throws Exception {
		return notificationMapper.getAllNotifications(userId);
	}

	@Override
	public int deleteOneNotification(int notificationId) throws Exception {
		return notificationMapper.deleteOneNotification(notificationId);
	}
}
