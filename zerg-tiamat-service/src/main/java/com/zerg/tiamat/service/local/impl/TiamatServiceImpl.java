package com.zerg.tiamat.service.local.impl;

import com.google.common.base.Preconditions;
import com.zerg.tiamat.common.http.response.Page;
import com.zerg.tiamat.common.http.response.PageInfo;
import com.zerg.tiamat.dto.UserAvailableModelDTO;
import com.zerg.tiamat.service.dao.DataModelService;
import com.zerg.tiamat.service.local.CacheService;
import com.zerg.tiamat.service.local.TiamatService;
import com.zerg.tiamat.vo.DataModelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:03
 */

@Service
public class TiamatServiceImpl implements TiamatService {


    @Resource
    private DataModelService dataModelService;

    @Resource
    private CacheService cacheService;

    /**
     * 通过用户id获取可用模型
     *
     * @param userAvailableModelDTO
     * @return
     */
    @Override
    public Page<DataModelVO> getAvailableDataModel(UserAvailableModelDTO userAvailableModelDTO) {
        Preconditions.checkArgument(Objects.nonNull(userAvailableModelDTO), "用户id不能为空");

        List<DataModelVO> userModel = dataModelService.selectByUserId(userAvailableModelDTO).stream()
                .map(dataModel -> {
                    DataModelVO dataModelVO = new DataModelVO();
                    BeanUtils.copyProperties(dataModel, dataModelVO);
                    return dataModelVO;
                }).collect(Collectors.toList());


        Integer count = dataModelService.countByUserId(userAvailableModelDTO.getUserId());
        PageInfo pageInfo = PageInfo.create(userAvailableModelDTO.getPageNum(), userAvailableModelDTO.getPageSize(), count);

        return Page.create(userModel, pageInfo);
    }
}
