package com.zerg.tiamat.service.local;

import com.zerg.tiamat.dao.Alarm;
import com.zerg.tiamat.vo.DataModelVO;

import java.util.Collection;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:11
 */

public interface CacheService {
    Collection<DataModelVO> getSystemDataModel();

    Collection<Alarm> getAllAlarm();

    Alarm getUserAlarm(String userEmail);

    void updateAlarm();
}
