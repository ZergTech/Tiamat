package com.zerg.tiamat.service.local.impl;

import com.zerg.tiamat.service.local.HealthCheckService;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:31
 */
@Service
public class HealthCheckServiceImpl implements HealthCheckService {
    @Override
    public String healthCheck() {
        return "你好，当前时间:" + Instant.now().toEpochMilli();
    }
}
