package project.spring.mvc.models;

import java.util.Date;

public class Post {
    private int id;
    private int blogId;
    private Date date;
    private String title;
    private String content;

    public Post() {
    }

    public Post(int id, int blogId, Date date, String title, String content) {
        this.id = id;
        this.blogId = blogId;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
