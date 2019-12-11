package com.zerg.tiamat.common.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalCount;

    public static PageInfo create(Integer pageNum, Integer pageSize, Integer totalCount) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalCount(totalCount);
        return pageInfo;
    }
}