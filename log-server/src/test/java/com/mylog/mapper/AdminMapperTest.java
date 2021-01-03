package com.mylog.mapper;

import com.mylog.BaseTest;
import com.mylog.domain.Admin;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;

public class AdminMapperTest extends BaseTest {
     public AdminMapperTest(){}
    @Resource
    private AdminMapper adminMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Admin admin = new Admin();
        admin.setPassword("123456");
        admin.setUserName("admin");
        int flag = adminMapper.insert(admin);
        Assert.assertEquals(1, flag);
    }

    @Test
    public void selectByPrimaryKey() {
        Admin admin = adminMapper.selectByPrimaryKey(0);
        Assert.assertNotNull(admin);
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}