package com.mylog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登陆日志的实体类的展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsDau {

    private String mid;

    private String logdate;

    private String uid;
    
    private String appid;

    private String area;

    private String os;

    private String ch;

    private String type;

    private String vs;

    private String loghour;

    private Long ts;

}