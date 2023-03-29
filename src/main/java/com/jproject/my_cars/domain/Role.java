package com.jproject.my_cars.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("ROLE_MEMBER","회원"),
    DEALER("ROLE_DEALER","딜러");

    private final String key;
    private final String title;

}
