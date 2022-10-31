package com.beedbclient.Service.impl;

import com.beedbclient.Entity.CollectionEntity;
import com.beedbclient.Entity.ComputerEntity;
import com.beedbclient.Entity.ProcessinfoEntity;
import com.beedbclient.MybatisUtil;
import com.beedbclient.Service.DbService;
import com.beedbclient.common.Collect;
import com.beedbclient.common.TranMessage;
import com.beedbclient.mapper.CollectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.mysql.cj.protocol.Message;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author yh
 * @date 2022/9/25 下午6:25
 */
public class DbServiceImpl implements DbService {
    private Logger logger=Logger.getLogger(DbServiceImpl.class.toString());
   static SqlSession sqlSession = MybatisUtil.getSqlSession();
   static   CollectMapper mapper = sqlSession.getMapper(CollectMapper.class);

    @Override
    public boolean AddCollItem(TranMessage.Message message) throws InvalidProtocolBufferException {
        Collect.collectInfo collectInfo = Collect.collectInfo.parseFrom(message.getBody());

        CollectionEntity build = CollectionEntity.builder()
                .node(message.getNode())
                .apiKey(message.getApikey())
                .swap_memory(collectInfo.getMeminfo().getSwapused())
                .sys_memory(collectInfo.getMeminfo().getMemused())
                .io_cpu(collectInfo.getCpu().getIo())
                .sys_cpu(collectInfo.getCpu().getSy())
                .us_cpu(collectInfo.getCpu().getUs())
                .disk(1).
                        build();
        mapper.AddCollection(build);

        Collect.processinfo proinfo = collectInfo.getProinfo();
        List<ProcessinfoEntity> list=new ArrayList<>();

        for (int i=0;i<proinfo.getProcessinfoCount()-1;i++){
            Collect.process processinfo = proinfo.getProcessinfo(i);
            ProcessinfoEntity pro = ProcessinfoEntity.builder()
                    .cid(build.getId())
                    .cpu(processinfo.getCpu())
                    .command(processinfo.getCommand())
                    .user(processinfo.getUser())
                    .mem(processinfo.getMem())
                    .pid(processinfo.getPid()).build();
            list.add(pro);
        }
        try {
            mapper.BatchProcess(list);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        logger.info("写入成功");
        return true;
    }

    @Override
    public boolean AddComputerInfo(TranMessage.Message info) throws InvalidProtocolBufferException {
        Collect.Computerinfo computerinfo = Collect.Computerinfo.parseFrom(info.getBody());
        if (mapper.HasComputerInfo(info.getNode())>0) {
            logger.info("该节点已存在！");
            return false;
        }
        ComputerEntity build = ComputerEntity.builder()
                .cpu_core(computerinfo.getCpuCore())
                .cpu_count(computerinfo.getCpuCount())
                .apikey(info.getApikey())
                .disk(computerinfo.getDiskCount())
                .main_board(computerinfo.getMainBoard())
                .mem(computerinfo.getMemCount())
                .Node(info.getNode())
                .date(LocalDateTime.now()).build();
        try {
            mapper.AddComputerInfo(build);

        }catch (Exception e){
            logger.info(e.toString());
        }
        return true;
    }


}
