package com.zerg.tiamat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : xuyang
 * @date : 2019-12-24 13:55
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerJResponse {
    /**
     * errno : 0
     * errmsg : success
     * dataset : done
     */

    private Integer errno;
    private String errmsg;
    private String dataset;
}
