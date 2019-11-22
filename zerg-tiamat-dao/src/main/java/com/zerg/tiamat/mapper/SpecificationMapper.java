package com.zerg.tiamat.mapper;

import com.zerg.tiamat.dao.Specification;
import com.zerg.tiamat.dao.SpecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecificationMapper {
    long countByExample(SpecificationExample example);

    int deleteByExample(SpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    int insertSelective(Specification record);

    List<Specification> selectByExample(SpecificationExample example);

    Specification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);
}