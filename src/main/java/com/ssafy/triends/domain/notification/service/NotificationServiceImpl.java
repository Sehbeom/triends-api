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
	public int sendPlanMemberInvitation(Map<String, String> userAndSenderAndPlanId) throws Exception {
		Map<String, Object> typeCastedParameter = castTypeStringToInteger(userAndSenderAndPlanId);
		return notificationMapper.sendPlanMemberInvitation(typeCastedParameter);
	}

	@Override
	public int sendFriendRequest(Map<String, String> userAndSenderId) throws Exception {
		Map<String, Object> typeCastedParameter = castTypeStringToInteger(userAndSenderId);
		return notificationMapper.sendFriendRequest(typeCastedParameter);
	}

	private Map<String, Object> castTypeStringToInteger(Map<String, String> parameter) {
		Map<String, Object> typeCastedParameter = new HashMap<>();

		for (Map.Entry<String, String> p : parameter.entrySet()) {
			typeCastedParameter.put(p.getKey(), p.getValue());
		}

		return typeCastedParameter;
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
