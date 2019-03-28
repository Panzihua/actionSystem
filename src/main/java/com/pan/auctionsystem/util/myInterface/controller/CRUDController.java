package com.pan.auctionsystem.util.myInterface.controller;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CRUDController<T> {
    public String selectAll(Model model, HttpServletRequest request);
    public List<T> selectByCondition(T condition);
    public String findOneById(Model model, int itemId, HttpServletRequest request);
    public String updateOneByModel(T model);
    public String deleteOneById(int modelId);
    public String addOneByModel(T model);
}
