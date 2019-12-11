package com.zerg.tiamat.mapper;

import com.zerg.tiamat.dao.Statement;
import com.zerg.tiamat.dao.StatementExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface StatementMapper {
    long countByExample(StatementExample example);

    int deleteByExample(StatementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Statement record);

    int insertSelective(Statement record);

    List<Statement> selectByExample(StatementExample example);

    Statement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByExample(@Param("record") Statement record, @Param("example") StatementExample example);

    int updateByPrimaryKeySelective(Statement record);

    int updateByPrimaryKey(Statement record);
}