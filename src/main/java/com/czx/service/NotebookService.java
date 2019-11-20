package com.czx.service;

import com.czx.bean.Notebook;
import com.czx.util.NotebookError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookService {
    @Autowired
    private NotebookService notebookDao;
    public List<Notebook> findSpecial(String userId){
        return notebookDao.findSpecial(userId);
    }
    public List<Notebook> findNormal(String userId){

        return notebookDao.findNormal(userId);
    }
    public void deleteNotebook(String id){
        notebookDao.deleteNotebook(id);
    }
    public NotebookError addNotebook(String notebookName, String userId){
        if(notebookName==null||notebookName.trim().length()==0){
            return NotebookError.NAME_NULL;
        }
        return NotebookError.SUCCESS;
    }

}
