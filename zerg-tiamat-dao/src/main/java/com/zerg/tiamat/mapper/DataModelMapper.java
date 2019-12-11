package com.zerg.tiamat.mapper;

import com.zerg.tiamat.dao.DataModel;
import com.zerg.tiamat.dao.DataModelExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataModelMapper {
    long countByExample(DataModelExample example);

    int deleteByExample(DataModelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataModel record);

    int insertSelective(DataModel record);

    List<DataModel> selectByExample(DataModelExample example);

    DataModel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataModel record, @Param("example") DataModelExample example);

    int updateByExample(@Param("record") DataModel record, @Param("example") DataModelExample example);

    int updateByPrimaryKeySelective(DataModel record);

    int updateByPrimaryKey(DataModel record);
}