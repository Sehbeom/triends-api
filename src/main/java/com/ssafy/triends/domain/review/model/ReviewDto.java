package com.ssafy.triends.domain.review.model;

import com.ssafy.triends.domain.plan.model.PlanDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDto {
	private int reviewId;
	private String subject;
	private int planId;
	private String planTitle;
	private String content;
	private int userId;
	private String userName;
	private String createdAt;
	private String modifiedAt;
	private int likes;
	private int scrapped;

	private PlanDto planInfo;
}
