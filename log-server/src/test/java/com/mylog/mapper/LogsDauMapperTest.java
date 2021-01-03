package com.mylog.mapper;

import com.mylog.BaseTest;
import com.mylog.domain.LogsDau;
import com.mylog.formvo.StatisticsVo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

public class LogsDauMapperTest extends BaseTest {

    @Resource
    private LogsDauMapper logsDauMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
        LogsDau logsDau = logsDauMapper.selectByPrimaryKey("mid_118", "2020-12-24");
        Assert.assertNotNull(logsDau);
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByOsTest(){
        List<HashMap<String,Object>> hashMapList = logsDauMapper.selectByOs();
        for (HashMap<String, Object> hashMap : hashMapList) {
            StatisticsVo mobilePhone = new StatisticsVo();
            mobilePhone.setName(hashMap.get("mobile")+"");
            mobilePhone.setValue(Integer.parseInt(hashMap.get("number")+""));
            System.out.println(mobilePhone);
        }
    }
}