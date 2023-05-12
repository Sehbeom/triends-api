package com.ssafy.triends.domain.attraction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipCode;
	private String tel;
	private String firstImage;
	private String firstImage2;
	private int readCount;
	private int sidoCode;
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String mlevel;
	private double rates;

}
