package com.zerg.tiamat.service.local.impl;

import com.zerg.tiamat.service.local.CacheService;
import com.zerg.tiamat.vo.DataModelVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:12
 */

@Service
public class CacheServiceImpl implements CacheService {
    @Override
    public List<DataModelVO> getSystemDataModel() {
        return null;
    }
}
