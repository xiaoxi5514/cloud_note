package com.czx.controller;

import com.czx.bean.Result;
import com.czx.bean.User;
import com.czx.service.UserService;
import com.czx.util.UserError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;


    @RequestMapping("/login.do")
    @ResponseBody
    public Object login(User user, HttpSession session){
        Result result=new Result();
        UserError ue=service.login(user);
        switch (ue){
            case USERNAME_NUll:
                result.setSuccess(false);
                result.setMsg("用户名不能为空");
                break;
            case PASSWORD_NULL:
                result.setSuccess(false);
                result.setMsg("密码不能为空");
                break;
            case USERNAME_OR_PASSWORD_ERROR:
                result.setSuccess(false);
                result.setMsg("用户名或密码错误");
                break;
        }
        User user1 = service.userFindByName(user.getName());
        result.setValue(user1);
        session.setAttribute("user",user1);
        return result;
    }
    @RequestMapping("/reg.do")
    @ResponseBody
    public Object register(User user){
        Result result=new Result();
        UserError ue=service.register(user);
        switch (ue){
            case USERNAME_NUll:
                result.setSuccess(false);
                result.setMsg("用户名不能为空");
                break;
            case PASSWORD_NULL:
                result.setSuccess(false);
                result.setMsg("密码不能为空");
                break;
            case USER_NAME_REPEAT:
                result.setSuccess(false);
                result.setMsg("用户名已存在");
                break;
            case USERNICKNAME_NULL:
                result.setSuccess(false);
                result.setMsg("昵称不能为空");
                break;
        }
        result.setValue(user);
        return result;
    }



    @RequestMapping("/updatePassword.do")
    @ResponseBody
    public Object userList(String id,String password,String old_password){
        Result result=new Result();
        UserError ue = service.updatePassword(id, password, old_password);
        switch (ue){
            case PASSWORD_ERROR:
                result.setSuccess(false);
                result.setMsg("原密码错误");
                break;
        }
        return result;
    }
    @RequestMapping("/logout.do")
    @ResponseBody
    public void logout(HttpSession session){
        session.removeAttribute("user");
    }

    @RequestMapping("/checkName.do")
    @ResponseBody
    public boolean checkName(String name) {
        User user = service.userFindByName(name);
        if(user==null){
            return true;
        }
        return false;
    }

}
