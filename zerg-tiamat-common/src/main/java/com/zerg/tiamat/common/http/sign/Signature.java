package com.zerg.tiamat.common.http.sign;


import com.zerg.tiamat.common.http.RequestProperties;

import java.util.Map;

/**
 * @author : xuyang
 * @date : 2019-09-19 20:51
 */
public interface Signature {
    String signatureGet(RequestProperties myRequestProperties);
    Map<String, Object> signatureForm(RequestProperties myRequestProperties, Map<String, Object> body);
    Object signatureJson(RequestProperties myRequestProperties, Object body);
}
