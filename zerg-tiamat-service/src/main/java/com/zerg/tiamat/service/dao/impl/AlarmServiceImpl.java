package com.zerg.tiamat.service.dao.impl;

import com.zerg.tiamat.common.enums.DeletedEnum;
import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.dao.AlarmExample;
import com.zerg.tiamat.mapper.AlarmMapper;
import com.zerg.tiamat.service.dao.AlarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-12-19 14:20
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    @Resource
    private AlarmMapper alarmMapper;

    /**
     * 查询全部提醒用户
     *
     * @return
     */
    @Override
    public List<Alarm> getAllUser() {

        AlarmExample alarmExample = new AlarmExample();
        alarmExample.createCriteria()
                .andDeletedEqualTo(DeletedEnum.UNDELETED.getCode());

        return alarmMapper.selectByExample(alarmExample);
    }
}
