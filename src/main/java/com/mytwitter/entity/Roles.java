package com.mytwitter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    USER("USER"), MODERATOR("MODERATOR");
    private String roleName;
}
