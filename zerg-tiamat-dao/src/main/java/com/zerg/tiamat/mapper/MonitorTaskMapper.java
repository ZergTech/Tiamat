package com.zerg.tiamat.mapper;

import com.zerg.tiamat.dao.MonitorTask;
import com.zerg.tiamat.dao.MonitorTaskExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MonitorTaskMapper {
    long countByExample(MonitorTaskExample example);

    int deleteByExample(MonitorTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorTask record);

    int insertSelective(MonitorTask record);

    List<MonitorTask> selectByExample(MonitorTaskExample example);

    MonitorTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonitorTask record, @Param("example") MonitorTaskExample example);

    int updateByExample(@Param("record") MonitorTask record, @Param("example") MonitorTaskExample example);

    int updateByPrimaryKeySelective(MonitorTask record);

    int updateByPrimaryKey(MonitorTask record);
}