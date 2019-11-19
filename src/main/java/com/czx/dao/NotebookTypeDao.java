package com.czx.dao;

import com.czx.bean.NotebookType;

import java.util.List;

public interface NotebookTypeDao {
    NotebookType findNormal();
    List<NotebookType> findSpecal();
}
