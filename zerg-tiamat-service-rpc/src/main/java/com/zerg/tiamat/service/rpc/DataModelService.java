package com.zerg.tiamat.service.rpc;

import com.zerg.tiamat.dto.DataSourceDTO;
import com.zerg.tiamat.dto.OperateDTO;

/**
 * @author : xuyang
 * @date : 2019-11-20 18:31
 */

public interface DataModelService {
    /**
     * 数据通用接口
     * @param dataSourceDTO
     * @return
     */
    OperateDTO operate(DataSourceDTO dataSourceDTO);
}
