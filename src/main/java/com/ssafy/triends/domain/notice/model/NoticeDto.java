package com.ssafy.triends.domain.notice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
	private int noticeId;
	private int userId;
	private String subject;
	private String content;
	private String createdAt;
	
}
