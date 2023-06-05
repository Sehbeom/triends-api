package com.ssafy.triends.domain.notification.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "NotificationDto(알림)", description = "알림 상세 정보를 담고 있다.")
public class NotificationDto {
	@ApiModelProperty(value = "알림 pk")
	private int notificationId;
	@ApiModelProperty(value = "알림 수신측 유저 정보 (pk)")
	private int receiver;
	@ApiModelProperty(value = "알림 송신측 유저 정보 (pk)")
	private int sender;
	@ApiModelProperty(value = "알림 유형 (plan or friends)")
	private String notificationType;
	@ApiModelProperty(value = "알림 부가 정보")
	private String additionalInfo;
	@ApiModelProperty(value = "플랜 멤버 초대 알림에 대한 플랜 정보 (pk)")
	private int planId;

}
