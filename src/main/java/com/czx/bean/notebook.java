package com.czx.bean;

import java.util.Date;

public class notebook {
    private String id;
    private User user;
    private notebook_type notebook_type;
    private String name;
    private Date createtime;

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

    public com.czx.bean.notebook_type getNotebook_type() {
        return notebook_type;
    }

    public void setNotebook_type(com.czx.bean.notebook_type notebook_type) {
        this.notebook_type = notebook_type;
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
                ", notebook_type=" + notebook_type +
                ", name='" + name + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
