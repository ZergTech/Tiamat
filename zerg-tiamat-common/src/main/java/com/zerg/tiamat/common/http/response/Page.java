package com.zerg.tiamat.common.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:42
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page<T> implements Serializable {
    private List<T> list;
    private PageInfo pageInfo;

    public static <T> Page<T> create(List<T> list, PageInfo pageInfo) {
        Page<T> page = new Page<>();
        page.setList(list);
        page.setPageInfo(pageInfo);
        return page;
    }
}
