package com.ssafy.triends.domain.attraction.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value = "AttractionDto(여행지)", description = "여행지의 상세 정보를 담고 있다.")
public class AttractionDto {
	@ApiModelProperty(value = "여행지 pk")
	private int contentId;
	@ApiModelProperty(value = "여행지 유형 정보 (pk)")
	private int contentTypeId;
	@ApiModelProperty(value = "여행지 이름")
	private String title;
	@ApiModelProperty(value = "여행지 설명")
	private String description;
	@ApiModelProperty(value = "여행지 주소")
	private String addr1;
	@ApiModelProperty(value = "여행지 상세 주소")
	private String addr2;
	@ApiModelProperty(value = "여행지 우편번호")
	private String zipCode;
	@ApiModelProperty(value = "여행지 전화번호")
	private String tel;
	@ApiModelProperty(value = "여행지 사진1")
	private String firstImage;
	@ApiModelProperty(value = "여행지 사진2")
	private String firstImage2;
	@ApiModelProperty(value = "여행지 상세정보 확인 횟수")
	private int readCount;
	@ApiModelProperty(value = "여행지 시/도 코드 정보 (pk)")
	private int sidoCode;
	@ApiModelProperty(value = "여행지 시/군/구 코드 정보 (pk)")
	private int gugunCode;
	@ApiModelProperty(value = "여행지 위도")
	private double latitude;
	@ApiModelProperty(value = "여행지 경도")
	private double longitude;
	@ApiModelProperty(value = "지도 api mlevel")
	private String mlevel;
	@JsonProperty(value = "rateAvg")
	@ApiModelProperty(value = "여행지 평균 평점")
	private double rate;

}
