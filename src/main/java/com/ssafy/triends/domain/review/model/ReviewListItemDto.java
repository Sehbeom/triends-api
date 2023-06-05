package com.ssafy.triends.domain.review.model;

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
@ApiModel(value = "ReviewListItemDto(리뷰 목록)", description = "리뷰 목록 페이지 내 각 리뷰 항목에 필요한 정보를 담고 있다.")
public class ReviewListItemDto {
    @ApiModelProperty(value = "리뷰 pk")
    private int reviewId;
    @ApiModelProperty(value = "리뷰 제목")
    private String subject;
    @ApiModelProperty(value = "리뷰 내용")
    private String content;
    @ApiModelProperty(value = "리뷰 작성자 유저 정보 (pk)")
    private int writerId;
    @ApiModelProperty(value = "리뷰 작성자 유저 정보 (유저 이름)")
    private String writerName;
    @ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (pk)")
    private int planId;
    @ApiModelProperty(value = "리뷰에 부여된 좋아요 수")
    private int likes;
    @ApiModelProperty(value = "리뷰가 스크랩 된 횟수")
    private int scrapped;
    @ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (플랜 시작일)")
    private String startDate;
    @ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (플랜 종료일)")
    private String endDate;
    @ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (플랜 썸네일 사진)")
    private String thumbnail;
    @ApiModelProperty(value = "작성된 리뷰의 플랜 정보 (여행지 목록)")
    private List<String> attractions;
}
