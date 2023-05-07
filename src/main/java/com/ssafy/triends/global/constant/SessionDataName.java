package com.ssafy.triends.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionDataName {
    USER_INFO("userInfo");

    private final String name;
}
