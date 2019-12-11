package com.zerg.tiamat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : xuyang
 * @date : 2019-11-22 18:37
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAvailableModelDTO {
    private Integer userId;
    private Integer pageNum;
    private Integer pageSize;

}
