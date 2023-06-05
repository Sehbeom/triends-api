package com.ssafy.triends.domain.comment.model;

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
@ApiModel(value = "CommentDto(댓글)", description = "댓글 정보를 담고 있다.")
public class CommentDto {
	@ApiModelProperty(value = "댓글 pk")
	private int commentId;
	@ApiModelProperty(value = "댓글이 작성된 리뷰 정보 (pk)")
	private int reviewId;
	@ApiModelProperty(value = "댓글을 작성한 유저 정보 (pk)")
	private int userId;
	@ApiModelProperty(value = "댓글을 작성한 유저 정보 (작성자 이름)")
	private String name;
	@ApiModelProperty(value = "댓글 내용")
	private String content;
	@ApiModelProperty(value = "댓글 작성 일시")
	private String createdAt;
}
