package com.zerg.tiamat.service.rpc;

import com.zerg.tiamat.dto.ActionDTO;
import com.zerg.tiamat.dto.StatementDTO;

/**
 * @author : xuyang
 * @date : 2019-11-20 18:31
 */

public interface DataModelService {
    /**
     * 数据通用接口
     * @param dataDTO
     * @return
     */
    ActionDTO calc(StatementDTO dataDTO);
}
