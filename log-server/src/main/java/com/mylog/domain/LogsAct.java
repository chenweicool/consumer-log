package com.mylog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户的点击类的实现
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsAct {

    private String act;

    private Integer count;

}