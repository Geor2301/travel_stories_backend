package com.gt9.travelstories.model;

import lombok.Data;

@Data
public class UserResponse {

    private User result;
    private String token;
}
