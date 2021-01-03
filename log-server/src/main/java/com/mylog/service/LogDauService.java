package com.mylog.service;

import com.mylog.formvo.StatisticsVo;

import java.util.List;

public interface LogDauService {

    /*根据用户的手持设备统计用户的信息*/
    List<StatisticsVo> statisticOfMobile();

    /*根据地域统计*/
    List<StatisticsVo> statisticOfArea();

    /*根据用户的来源统计*/
    List<StatisticsVo> statisticOfOrigin();

    /*根据用户在线时长*/
    List<StatisticsVo> statisticOfHour();

}
