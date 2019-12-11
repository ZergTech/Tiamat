package com.zerg.tiamat.service.local;

import com.zerg.tiamat.vo.DataModelVO;

import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:11
 */

public interface CacheService {
    List<DataModelVO> getSystemDataModel();
}
