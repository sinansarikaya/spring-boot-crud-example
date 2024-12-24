package com.github.dto;

import lombok.Data;

@Data
public class RequestParams {
    private int page = 0;
    private int size = 10;
    private String sort = "createdAt";
    private String direction = "ASC";
}
