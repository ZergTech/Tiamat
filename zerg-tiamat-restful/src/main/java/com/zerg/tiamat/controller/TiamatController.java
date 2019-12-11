package com.zerg.tiamat.controller;

import com.zerg.tiamat.common.http.response.BaseResponse;
import com.zerg.tiamat.dto.UserAvailableModelDTO;
import com.zerg.tiamat.service.local.TiamatService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:49
 */

@Slf4j
@RestController
@RequestMapping(value = "/tiamat/platform", produces = "application/json; charset=utf-8")
@Api(tags = "数据模型平台", description = "rest")
public class TiamatController {

    @Resource
    private TiamatService tiamatService;

    @PostMapping("/getModel")
    public BaseResponse getAvailableDataModel(UserAvailableModelDTO userAvailableModelDTO) {
        return BaseResponse.success(tiamatService.getAvailableDataModel(userAvailableModelDTO));
    }
}

