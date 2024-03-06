package com.glushkov.dbcrud.model;

import java.util.Date;
import java.util.List;

public class Post extends BaseItem {
    private String title;
    private String content;
    private List<Label> labels;

    private Date created;
    private Date updated;
    private Status status;

    public Post() {
        super();
    }

    public Post(long id) {
        super(id);
    }

    public Post(long id, Date created, Status status) {
        this(id);
        this.created = created;
        this.status = status;
    }

    public Post(long id, String title, String content, Date created, Date updated, Status status) {
        this(id, created, status);
        this.title = title;
        this.content = content;
        this.updated = updated;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Date getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Post{" + super.toString() +
                "status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", labels=" + labels +
                "}\n";
    }
}
