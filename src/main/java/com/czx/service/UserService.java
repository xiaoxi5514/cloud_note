package com.czx.service;

import com.czx.bean.User;
import com.czx.dao.UserDao;
import com.czx.util.Md5Util;
import com.czx.util.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDao dao;
    @Autowired
    private NotebookService notebookService;
    public List<User> userList(){
        List<User> users = dao.findAll();
        return users;
    }
    public User userFindByName(String name){
        return  dao.findByName(name);
    }
    public void deleteUser(String id){
        dao.delete(id);
    }
   @Transactional
    public UserError register(User user){
        if(user.getName()==null||user.getName().trim().length()==0){
            return UserError.USERNAME_NUll;
        }
        if(user.getNickname()==null||user.getNickname().trim().length()==0){
            return UserError.USERNICKNAME_NULL;
        }
        if(user.getPassword()==null||user.getPassword().trim().length()==0){
            return  UserError.PASSWORD_NULL;
        }
        User u = dao.findByName(user.getName());
        if(u!=null){
            return UserError.USER_NAME_REPEAT;
        }
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setPassword(Md5Util.md5(user.getPassword()));
        dao.add(user);
        notebookService.initSpecialNotebook(user.getId());
        return UserError.SUCCESS;
    }
    @Transactional(readOnly = true)
    public UserError login(User user){
        if(user.getName()==null||user.getName().trim().length()==0){
            return UserError.USERNAME_NUll;
        }
        if(user.getPassword()==null||user.getPassword().trim().length()==0){
            return  UserError.PASSWORD_NULL;
        }
        User u = dao.findByName(user.getName());

        if(u==null || !u.getPassword().equals(Md5Util.md5(user.getPassword()))){
            return UserError.USERNAME_OR_PASSWORD_ERROR;
        }
        return UserError.SUCCESS;
    }
    @Transactional
    public UserError updatePassword(String id,String password,String old_password){
        User user=dao.findById(id);
        if(!user.getPassword().equals(Md5Util.md5(old_password))){
            return UserError.PASSWORD_ERROR;
        }
        user.setPassword(Md5Util.md5(password));
        dao.update(user);
        return UserError.SUCCESS;
    }


}
