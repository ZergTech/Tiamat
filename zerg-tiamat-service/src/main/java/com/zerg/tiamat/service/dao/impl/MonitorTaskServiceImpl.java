package com.zerg.tiamat.service.dao.impl;

import com.zerg.tiamat.common.enums.DeletedEnum;
import com.zerg.tiamat.dao.MonitorTask;
import com.zerg.tiamat.dao.MonitorTaskExample;
import com.zerg.tiamat.mapper.MonitorTaskMapper;
import com.zerg.tiamat.service.dao.MonitorTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-12-19 23:40
 */
@Service
public class MonitorTaskServiceImpl implements MonitorTaskService {

    @Resource
    private MonitorTaskMapper monitorTaskMapper;

    @Override
    public List<MonitorTask> getAllTask() {
        MonitorTaskExample example = new MonitorTaskExample();
        example.createCriteria()
                .andDeletedEqualTo(DeletedEnum.UNDELETED.getCode());

        return monitorTaskMapper.selectByExample(example);
    }
}
