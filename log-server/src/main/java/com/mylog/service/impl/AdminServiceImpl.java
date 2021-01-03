package com.mylog.service.impl;

import com.mylog.domain.Admin;
import com.mylog.mapper.AdminMapper;
import com.mylog.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public boolean selectByUserName(String userName,String password) {
        Admin admin = adminMapper.selectByUserName(userName);
        if(admin == null){
            throw new RuntimeException("该用户不存在");
        }
        String passwordDb = admin.getPassword();
        if(!password.equals(passwordDb)){
            throw new RuntimeException("密码不正确");
        }
        return true;
    }
}
