package com.zerg.tiamat.controller;

import com.zerg.tiamat.common.http.response.BaseResponse;
import com.zerg.tiamat.dto.AlarmDTO;
import com.zerg.tiamat.service.local.CacheService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : xuyang
 * @date : 2019-12-19 16:25
 */

@Slf4j
@RestController
@RequestMapping(value = "/tiamat/alarm", produces = "application/json; charset=utf-8")
@Api(tags = "数据报警平台", description = "rest")
public class AlarmController {

    @Resource
    private CacheService cacheService;

    @GetMapping("/updateAlarm")
    public BaseResponse updateAlarmCache(){
        cacheService.updateAlarm();
        return BaseResponse.success();
    }

    @PostMapping("/updateAlarmByEmail")
    public BaseResponse updateAlarm(@ModelAttribute AlarmDTO alarmDTO){
        cacheService.updateAlarm(alarmDTO);
        return BaseResponse.success();
    }

}
