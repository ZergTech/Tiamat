package com.zerg.tiamat.service.rpc.notify;

/**
 * @author : xuyang
 * @date : 2019-12-23 13:28
 */
public interface NotifyRpcService {
    Boolean sendMessage(String subject, String text);
}
