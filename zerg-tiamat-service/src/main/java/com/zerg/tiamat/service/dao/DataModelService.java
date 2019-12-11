package com.zerg.tiamat.service.dao;

import com.zerg.tiamat.dao.DataModel;
import com.zerg.tiamat.dto.UserAvailableModelDTO;

import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:04
 */
public interface DataModelService {

    List<DataModel> selectByUserId(UserAvailableModelDTO userAvailableModelDTO);


    Integer countByUserId(Integer userId);
}
