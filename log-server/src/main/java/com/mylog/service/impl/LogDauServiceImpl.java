package com.mylog.service.impl;

import com.mylog.formvo.StatisticsVo;
import com.mylog.mapper.LogsDauMapper;
import com.mylog.service.LogDauService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LogDauServiceImpl implements LogDauService{

    @Resource
    private LogsDauMapper logsDauMapper;

    /**
     * 返回具体设备统计信息
     * @return
     */
    @Override
    public List<StatisticsVo> statisticOfMobile() {
        List<HashMap<String,Object>> list = logsDauMapper.selectByOs();
        List<StatisticsVo> result = new ArrayList<>();
        StatisticsVo statisticsVo ;

        for (HashMap<String, Object> hashMap : list) {
            statisticsVo = new StatisticsVo();
            statisticsVo.setName(hashMap.get("mobile")+"");
            statisticsVo.setValue(Integer.parseInt(hashMap.get("number")+""));
            result.add(statisticsVo);
        }
        return result;
    }

    @Override
    public List<StatisticsVo> statisticOfArea() {
        List<HashMap<String,Object>> list = logsDauMapper.selectByArea();
        List<StatisticsVo> result = new ArrayList<>();
        StatisticsVo statisticsVo ;

        for (HashMap<String, Object> hashMap : list) {
            statisticsVo = new StatisticsVo();
            statisticsVo.setName(hashMap.get("area")+"");
            statisticsVo.setValue(Integer.parseInt(hashMap.get("number")+""));
            result.add(statisticsVo);
        }
        return result;
    }

    @Override
    public List<StatisticsVo> statisticOfOrigin() {
        List<HashMap<String,Object>> list = logsDauMapper.selectByOrigin();
        List<StatisticsVo> result = new ArrayList<>();
        StatisticsVo statisticsVo ;

        for (HashMap<String, Object> hashMap : list) {
            statisticsVo = new StatisticsVo();
            statisticsVo.setName(hashMap.get("origin")+"");
            statisticsVo.setValue(Integer.parseInt(hashMap.get("number")+""));
            result.add(statisticsVo);
        }
        return result;
    }

    @Override
    public List<StatisticsVo> statisticOfHour() {
        List<HashMap<String,Object>> list = logsDauMapper.selectByHour();
        List<StatisticsVo> result = new ArrayList<>();
        StatisticsVo statisticsVo ;

        for (HashMap<String, Object> hashMap : list) {
            statisticsVo = new StatisticsVo();
            statisticsVo.setName(hashMap.get("logHour")+"");
            statisticsVo.setValue(Integer.parseInt(hashMap.get("number")+""));
            result.add(statisticsVo);
        }
        return result;
    }
}
