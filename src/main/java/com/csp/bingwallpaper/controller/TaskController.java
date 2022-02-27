package com.csp.bingwallpaper.controller;

import com.csp.bingwallpaper.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author csp
 * @date 2022/2/27 14:08
 * @description
 */
@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/task",method = RequestMethod.GET)
    public String task() throws NoSuchMethodException {
        taskService.newTask();
        return "task";
    }
}
