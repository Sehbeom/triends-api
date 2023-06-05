package com.ssafy.triends.domain.review.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "AttractionRateDto(여행지 평점)", description = "리뷰 작성 과정에서 유저가 여행지에 부여한 평점 정보를 담고 있다.")
public class AttractionRateDto {
    @ApiModelProperty(value = "평점 pk")
    private int ratesId;
    @ApiModelProperty(value = "평점을 부여한 유저 정보 (pk)")
    private int userId;
    @ApiModelProperty(value = "평점 부여 대상 여행지 정보 (pk)")
    private int contentId;
    @ApiModelProperty(value = "평점")
    private int score;
    @ApiModelProperty(value = "평점이 부여된 리뷰 정보 (pk)")
    private int reviewId;
}
