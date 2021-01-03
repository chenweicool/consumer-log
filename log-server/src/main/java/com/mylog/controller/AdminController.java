package com.mylog.controller;

import com.alibaba.fastjson.JSONObject;
import com.mylog.service.AdminService;
import com.mylog.utils.Constants;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "管理员管理")
@RestController
@RequestMapping("/user")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/login")
   public Object login(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean result = adminService.selectByUserName(name, password);
        if (result) {
            jsonObject.put(Constants.CODE, 1);
            jsonObject.put(Constants.MSG, "登陆成功");
            // 将用户设置进session中去
            session.setAttribute(Constants.NAME, name);
            return jsonObject;
        }
        jsonObject.put(Constants.CODE,2);
        jsonObject.put(Constants.MSG, "用户名或密码错误");
        return jsonObject;
   }
}
