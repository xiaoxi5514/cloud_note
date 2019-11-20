package com.czx.bean;

import java.util.Date;

public class Notebook {
    private String id;
    private User user;
    private NotebookType notebookType;
    private String name;
    private Date createtime;

    public Notebook(String id, User user, NotebookType notebookType, String name, Date createtime) {
        this.id = id;
        this.user = user;
        this.notebookType = notebookType;
        this.name = name;
        this.createtime = createtime;
    }

    public Notebook(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public Notebook() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotebookType getNotebookType() {
        return notebookType;
    }

    public void setNotebookType(NotebookType notebookType) {
        this.notebookType = notebookType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "notebook{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", notebookType=" + notebookType +
                ", name='" + name + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
