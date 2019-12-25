package com.zerg.tiamat.service.dao;

import com.zerg.tiamat.dao.Alarm;

import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-12-19 14:19
 */
public interface AlarmService {

    /**
     * 查询全部提醒用户
     * @return
     */
    List<Alarm> getAllUser();

}
