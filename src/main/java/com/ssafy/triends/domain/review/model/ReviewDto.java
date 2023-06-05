package com.ssafy.triends.domain.review.model;

import com.ssafy.triends.domain.plan.model.PlanDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ReviewDto(리뷰 상세)", description = "리뷰 상세 정보를 담고 있다.")
public class ReviewDto {
	@ApiModelProperty(value = "리뷰 pk")
	private int reviewId;
	@ApiModelProperty(value = "리뷰 제목")
	private String subject;
	@ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (pk)")
	private int planId;
	@ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (플랜 이름)")
	private String planTitle;
	@ApiModelProperty(value = "리뷰 내용")
	private String content;
	@ApiModelProperty(value = "리뷰를 작성한 유저 정보 (pk)")
	private int userId;
	@ApiModelProperty(value = "리뷰를 작성한 유저 정보 (유저 이름)")
	private String userName;
	@ApiModelProperty(value = "리뷰 작성 일시")
	private String createdAt;
	@ApiModelProperty(value = "리뷰 수정 일시")
	private String modifiedAt;
	@ApiModelProperty(value = "리뷰에 부여된 좋아요 수")
	private int likes;
	@ApiModelProperty(value = "리뷰가 스크랩 된 횟수")
	private int scrapped;
	@ApiModelProperty(value = "형재 로그인한 유저가 해당 리뷰를 좋아요 할 수 있는지 여부")
	private boolean canLike;

	@ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (상세정보)")
	private PlanDto planInfo;
	@ApiModelProperty(value = "작성된 리뷰의 여행지에 부여된 평점 정보 목록")
	private List<AttractionRateDto> rateInfo;
}
