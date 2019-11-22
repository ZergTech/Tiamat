package com.zerg.tiamat.controller;

import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@RestController
@Api(value = "健康检测", tags = {"健康检测"}, description = "Restful")
@RequestMapping("/")
public class HealthController {

    private static final int STATUS = 200;
    private static final String RETURN_MSG = "ok";

    @GetMapping("/healthCheck")
    public String healthCheck(HttpServletResponse response){
        log.info("==================== healthCheck ====================");
        response.setStatus(STATUS);
        return RETURN_MSG;
    }

    /**
     * readiness 在连续 3 次 check 失败后，会将当前流量摘除，应用不会重启。当下一次检测正常时，就会恢复流量
     *
     * @param response
     * @return
     */
    @GetMapping(value = "/readinessCheck")
    public String readinessCheck(HttpServletResponse response) {
        log.info("==================== readinessCheck ====================");
        response.setStatus(STATUS);
        return RETURN_MSG;
    }

}
