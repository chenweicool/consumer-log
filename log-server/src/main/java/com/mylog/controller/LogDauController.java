package com.mylog.controller;

import com.mylog.domain.LogsDau;
import com.mylog.formvo.StatisticsVo;
import com.mylog.service.LogDauService;
import com.mylog.service.LogsActService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户综合信息统计管理")
@RestController
@RequestMapping("/logdau")
public class LogDauController {

    @Resource
    private LogDauService logDauService;

    /**
     * 返回手机的统计信息
     * @return
     */
    @GetMapping("/mobile")
    public Object getStatisticOfMobile(){
        List<StatisticsVo>  list = logDauService.statisticOfMobile();
        String[] strMobile = new String[]{"安卓用户","苹果用户","其他"};
        for (int i = 0; i < list.size() ; i++) {
               list.get(i).setName(strMobile[i]);
        }
         return  list;
    }

    /**
     * 根据地域返回统计信息
     * @return
     */
    @GetMapping("/area")
    public Object getStatisticOfArea(){
        return logDauService.statisticOfArea();
    }

    /**
     * 根据下载源返回具体的信息
     * @return
     */
    @GetMapping("/origin")
    public Object getStatisticOfOrigin(){
        return logDauService.statisticOfOrigin();
    }

    /**
     * 根据用户时长返回具体的信息
     * @return
     */
    @GetMapping("/hour")
    public Object getStatisticOfHour(){
        return logDauService.statisticOfHour();
    }
}
