package com.kamelia.webservice.dto;

public class Comment {

    private String content;
    private int stars;

    public Comment() {
    }

    public Comment(String content, int stars) {
        this.content = content;
        this.stars = stars;
    }

    public String getContent() {
        return content;
    }

    public int getStars() {
        return stars;
    }

}
