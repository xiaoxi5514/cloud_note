package com.czx.dao;

import com.czx.bean.Notebook;
import com.czx.bean.User;

import java.util.List;

public interface NotebookDao {
    void add(Notebook notebook);
    void update(Notebook notebook);
    void delete(String id);
    List<Notebook> findByUserSpecial(User user);
    List<Notebook> findByUserNormal(User user);
    Notebook findByName(Notebook notebook);

}
