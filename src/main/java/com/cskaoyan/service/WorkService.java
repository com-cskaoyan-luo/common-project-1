package com.cskaoyan.service;

import com.cskaoyan.bean.Work;

import java.util.List;

public interface WorkService {
    List<Work> queryAllWork();

    List<Work> queryWork(int page, int rows);

    Work queryWorkById(String id);

    int updateSingleWork(Work work);

    int insertWork(Work work);

    int deleteBatch(List<String> strings);

    List<Work> searchWorkById(String searchValue, int page, int rows);

    List<Work> searchWorkByProduct(String searchValue, int page, int rows);

    List<Work> searchWorkByDevice(String searchValue, int page, int rows);

    List<Work> searchWorkByProcess(String searchValue, int page, int rows);
}
