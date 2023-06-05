package com.ssafy.triends.domain.notice.model;

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
@ApiModel(value = "NoticeDto(공지사항)", description = "공지사항의 상세 정보를 담고 있다.")
public class NoticeDto {
	@ApiModelProperty(value = "공지사항 pk")
	private int noticeId;
	@ApiModelProperty(value = "공지사항을 작성한 유저 정보 (pk)")
	private int userId;
	@ApiModelProperty(value = "공지사항 제목")
	private String subject;
	@ApiModelProperty(value = "공지사항 내용")
	private String content;
	@ApiModelProperty(value = "공지사항 작성 일시")
	private String createdAt;
	
}
