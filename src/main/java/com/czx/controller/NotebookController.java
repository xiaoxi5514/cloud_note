package com.czx.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notebook")
public class NotebookController {
    @GetMapping
    public Object notebookList(){
        System.out.println("获取列表");
        return null;
    }
    @PostMapping
    public Object addNotebook(){
        System.out.println("添加笔记本");
        return null;
    }
    @PutMapping
    public Object updateNotebook(){
        System.out.println("修改笔记本");
        return null;
    }
    @DeleteMapping
    public Object deleteNotebook(){
        System.out.println("删除笔记本");
        return null;
    }
}
