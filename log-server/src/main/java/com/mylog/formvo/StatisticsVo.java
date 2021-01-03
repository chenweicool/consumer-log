package com.mylog.formvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用来转换统计出的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsVo {
    private String name;
    private Integer value;
}
