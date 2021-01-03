package com.mylog.service.impl;

import com.mylog.domain.LogsAct;
import com.mylog.mapper.LogsActMapper;
import com.mylog.service.LogsActService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogsActServiceImpl implements LogsActService{

    @Resource
    private LogsActMapper logsActMapper;

    @Override
    public List<LogsAct> getAllAct() {
        return logsActMapper.selectAll();
    }
}
