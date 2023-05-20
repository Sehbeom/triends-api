package com.ssafy.triends.domain.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

	private int notificationId;
	private int receiver;
	private int sender;
	private String notificationType;
	private String additionalInfo;

}
