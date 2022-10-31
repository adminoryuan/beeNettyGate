package com.beedbclient.mapper;

import com.beedbclient.Entity.CollectionEntity;
import com.beedbclient.Entity.ComputerEntity;
import com.beedbclient.Entity.ProcessinfoEntity;

import java.util.List;

/**
 * @author yh
 * @date 2022/9/25 下午6:05
 */
public interface CollectMapper {
        int AddCollection(CollectionEntity info);

        int BatchProcess(List<ProcessinfoEntity> list);

        int HasComputerInfo(String node);

        int AddComputerInfo(ComputerEntity entity);
}
