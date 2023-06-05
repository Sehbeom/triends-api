package com.ssafy.triends.domain.plan.model;

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
@ApiModel(value = "PlanMemberDto(플랜 멤버)", description = "플랜 멤버 정보를 담고 있다.")
public class PlanMemberDto {
    @ApiModelProperty(value = "플랜 멤버에 소속된 유저 정보 (pk)")
    private int userId;
    @ApiModelProperty(value = "플랜 멤버에 소속된 유저 정보 (유저 이름)")
    private String name;
    @ApiModelProperty(value = "플랜 멤버에 소속된 유저 정보 (유저 프로필 사진)")
    private String profileimg;
}
