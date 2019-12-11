package com.zerg.tiamat.service.dao.impl;

import com.zerg.tiamat.dao.DataModel;
import com.zerg.tiamat.dto.UserAvailableModelDTO;
import com.zerg.tiamat.mapper.DataModelMapper;
import com.zerg.tiamat.service.dao.DataModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:05
 */

@Service
public class DataModelServiceImpl implements DataModelService {

    @Resource
    private DataModelMapper dataModelMapper;

    @Override
    public List<DataModel> selectByUserId(UserAvailableModelDTO userAvailableModelDTO) {
        return null;
    }

    @Override
    public Integer countByUserId(Integer userId) {
        return null;
    }
}
