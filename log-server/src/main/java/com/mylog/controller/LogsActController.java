package com.mylog.controller;

import com.mylog.domain.LogsAct;
import com.mylog.domain.LogsDau;
import com.mylog.service.LogsActService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户行为信息统计")
@RestController
@Slf4j
@RequestMapping("/logsact")
public class LogsActController {

    @Resource
    private LogsActService logsActService;

    @GetMapping("/all")
    public Object  getAllLogsAct(){
        String[] userBehave = new String[]{"加入购物车","评论人数","收藏商品人数","浏览人数","领优惠券的人数",};
        List<LogsAct> logsDauList = logsActService.getAllAct();
        for (int i = 0; i < logsDauList.size();i++) {
               logsDauList.get(i).setAct(userBehave[i]);
        }
        return logsDauList ;
    }
}
