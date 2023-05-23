package com.ssafy.triends.domain.notification.service;

import com.ssafy.triends.domain.notification.mapper.NotificationMapper;
import com.ssafy.triends.domain.notification.model.NotificationDto;
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
	public int sendPlanMemberInvitation(Map<String, Object> receiverAndPlanAndUserId) throws Exception {
		return notificationMapper.sendOnePlanMemberInvitation(receiverAndPlanAndUserId);
	}

	@Override
	public int sendFriendRequest(Map<String, Object> receiverAndUserId) throws Exception {
		return notificationMapper.sendFriendRequest(receiverAndUserId);
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
