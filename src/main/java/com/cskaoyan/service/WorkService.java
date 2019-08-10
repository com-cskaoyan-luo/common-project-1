package com.cskaoyan.service;

import com.cskaoyan.bean.Work;

import java.util.List;

public interface WorkService {
    List<Work> queryAllWork();

    List<Work> queryWork(int page, int rows);
}
