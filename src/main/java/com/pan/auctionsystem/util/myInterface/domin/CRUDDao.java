package com.pan.auctionsystem.util.myInterface.domin;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CRUDDao<T> {
    public List<T> selectAll();
    public List<T> selectByCondition(@Param("model") T model);
    public T findOneById(@Param("modelId") int id);
    public int updateOneByModel(@Param("model") T model);
    public int deleteOneById(@Param("modelId") int id);
    public int addOneByModel(@Param("model") T model);
}
