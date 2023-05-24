package com.ssafy.triends.domain.review.model;

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
public class ReviewListItemDto {
    private int reviewId;
    private String subject;
    private String content;
    private int writerId;
    private String writerName;
    private int planId;
    private int likes;
    private int scrapped;
    private String startDate;
    private String endDate;
    private String thumbnail;
    private List<String> attractions;
}
