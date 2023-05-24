package com.ssafy.triends.domain.comment.model;

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
public class CommentDto {
	private int commentId;
	private int reviewId;
	private int userId;
	private String name;
	private String content;
	private String createdAt;
}
