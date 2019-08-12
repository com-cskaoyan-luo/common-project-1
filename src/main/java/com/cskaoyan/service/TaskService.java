package com.cskaoyan.service;

import com.cskaoyan.bean.Task;

import java.util.List;

public interface TaskService {
    List<Task> queryTask(int page, int rows);

    int insertTask(Task task);

    int updateSingleTask(Task task);

    int deleteBatch(List<String> strings);

    List<Task> searchTaskById(String searchValue, int page, int rows);

    List<Task> searchTaskByWorkId(String searchValue, int page, int rows);

    List<Task> searchTaskByManufactureId(String searchValue, int page, int rows);
}
