package com.mylog.mapper;

import com.mylog.BaseTest;
import com.mylog.domain.LogsAct;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class LogsActMapperTest extends BaseTest {

    @Resource
    private LogsActMapper logsActMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
        LogsAct logsAct = new LogsAct();
        logsAct = logsActMapper.selectByPrimaryKey("addCart");
        Assert.assertNotNull(logsAct);

    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}