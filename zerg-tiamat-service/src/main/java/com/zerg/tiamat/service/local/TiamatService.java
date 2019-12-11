package com.zerg.tiamat.service.local;

import com.zerg.tiamat.common.http.response.Page;
import com.zerg.tiamat.dto.UserAvailableModelDTO;
import com.zerg.tiamat.vo.DataModelVO;

/**
 * @author : xuyang
 * @date : 2019-11-22 17:57
 */

public interface TiamatService {
    /**
     * 通过用户id获取全部可用模型
     *
     * @param userAvailableModelDTO
     * @return
     */
    Page<DataModelVO> getAvailableDataModel(UserAvailableModelDTO userAvailableModelDTO);

}
