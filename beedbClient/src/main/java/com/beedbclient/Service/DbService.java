package com.beedbclient.Service;

import com.beedbclient.Entity.ProcessinfoEntity;
import com.beedbclient.common.TranMessage;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author yh
 * @date 2022/9/25 下午6:24
 */
public interface DbService {

    boolean AddCollItem(TranMessage.Message collect) throws InvalidProtocolBufferException;

    boolean AddComputerInfo(TranMessage.Message info) throws InvalidProtocolBufferException;
}
