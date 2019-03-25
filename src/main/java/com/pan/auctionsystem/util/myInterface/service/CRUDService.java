package com.pan.auctionsystem.util.myInterface.service;


import java.util.List;

public interface CRUDService<T> {
    public List<T> selectAll();
    public List<T> selectByCondition(T model);
    public T findOneById(int id);
    public int updateOneByModel(T model);
    public int deleteOneById(int id);
    public int addOneByModel(T model);
}
