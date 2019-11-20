package com.czx.service;

import com.czx.bean.Notebook;
import com.czx.bean.NotebookType;
import com.czx.bean.User;
import com.czx.dao.NotebookDao;
import com.czx.dao.NotebookTypeDao;
import com.czx.util.NotebookError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NotebookService {
    @Autowired
    private NotebookDao notebookDao;
    @Autowired
    private NotebookTypeDao notebookTypeDao;
    @Transactional
    public List<Notebook> findSpecial(String userId){
        return notebookDao.findByUserSpecial(new User(userId));
    }
    @Transactional
    public List<Notebook> findNormal(String userId){

        return notebookDao.findByUserNormal(new User(userId));
    }
    @Transactional
    public void deleteNotebook(String id){
        System.out.println(notebookDao);
        System.out.println(notebookTypeDao);
        notebookDao.delete(id);
    }
    @Transactional
    public NotebookError addNotebook(String notebookName, String userId){
        if(notebookName==null||notebookName.trim().length()==0){
            return NotebookError.NAME_NULL;
        }
        Notebook nb=new Notebook();
        nb.setName(notebookName);
        nb.setUser(new User(userId));
        Notebook notebook = notebookDao.findByName(nb);
        if(notebook!=null){
            return NotebookError.NAME_REPEAT;
        }
        nb.setId(UUID.randomUUID().toString());
        nb.setCreatetime(new Date());
        NotebookType notebookType= notebookTypeDao.findNormal();
        nb.setNotebookType(notebookType);
        notebookDao.add(nb);
        return NotebookError.SUCCESS;
    }
    @Transactional
    public NotebookError updateNotebook(Notebook nb){
        if(nb.getName()==null||nb.getName().trim().length()==0){
            return NotebookError.NAME_NULL;
        }
        Notebook notebook = notebookDao.findByName(nb);
        if(notebook!=null){
            return NotebookError.NAME_REPEAT;
        }
        notebookDao.update(nb);
        return NotebookError.SUCCESS;
    }
    @Transactional
    public void initSpecialNotebook(String userId){
        List<NotebookType> nbts = notebookTypeDao.findSpecal();
        for(NotebookType nbt:nbts){
            Notebook nb=new Notebook();
            nb.setName(nbt.gettDesc());
            nb.setNotebookType(new NotebookType(nbt.getId()));
            nb.setCreatetime(new Date());
            nb.setId(UUID.randomUUID().toString());
            nb.setUser(new User(userId));
            notebookDao.add(nb);
        }
    }

}
