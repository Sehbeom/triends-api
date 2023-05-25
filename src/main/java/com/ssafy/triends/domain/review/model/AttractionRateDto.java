package com.ssafy.triends.domain.review.model;

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
public class AttractionRateDto {
    private int ratesId;
    private int userId;
    private int contentId;
    private int score;
    private int reviewId;
}
