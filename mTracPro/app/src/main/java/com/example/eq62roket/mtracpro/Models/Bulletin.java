package com.example.eq62roket.mtracpro.Models;

/**
 * Created by eq62roket on 1/23/18.
 */

public class Bulletin {
    private String id, date, content;

    public Bulletin(String id, String date, String content){
        this.setId(id);
        this.setDate(date);
        this.setContent(content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
