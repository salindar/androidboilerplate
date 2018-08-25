package com.salinda.androidboilerplate.model;

/**
 * Created by: Salinda Rathnayeka on 25/08/2018.
 * Email: salindakrishantha@gmail.com
 * IMPORTANT This class is for demonstration purpose only can be
 * removed from the codebase if needed
 */
public class Comment extends BaseModel {
    private String userId;
    private int id;
    private String title;
    private String body;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
