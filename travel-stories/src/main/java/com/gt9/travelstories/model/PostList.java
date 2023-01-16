package com.gt9.travelstories.model;


import lombok.Data;

import java.util.List;

@Data
public class PostList {
    private List<PostMessage> data;
    private int currentPage;
    private int numberOfPages;
}
