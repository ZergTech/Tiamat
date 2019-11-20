package com.zerg.tiamat.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author : xuyang
 * @date : 2019-11-21 01:05
 */

public class DataSourceDTO implements Serializable {
    private List<CandlestickDTO> list;
    private UserInfoDTO userInfoDTO;
    private Object ext;

    public DataSourceDTO() {
    }

    public List<CandlestickDTO> getList() {
        return list;
    }

    public void setList(List<CandlestickDTO> list) {
        this.list = list;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }
}
