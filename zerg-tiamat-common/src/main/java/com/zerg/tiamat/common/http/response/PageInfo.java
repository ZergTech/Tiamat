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
}