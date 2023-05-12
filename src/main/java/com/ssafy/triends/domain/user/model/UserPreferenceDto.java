package com.ssafy.triends.domain.user.model;

import com.ssafy.triends.global.model.ContentType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceDto {
    private int userId;
    private List<ContentType> contentTypes;
    private int preferenceBitFormat = -1;

}
