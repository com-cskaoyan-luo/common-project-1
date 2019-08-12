package com.cskaoyan.service;

import com.cskaoyan.bean.Custom;

import java.util.List;

public interface CustomService {
    Custom queryCustomById(String id);

    List<Custom> queryAllCustom();

    int updateSingleCustom(Custom custom);

    List<Custom> queryCustom(int page, int rows);

    int insertCustom(Custom custom);

    int deleteBatch(List<String> strings);

    List<Custom> searchCustomById(String searchValue, int page, int rows);

    List<Custom> searchCustomByName(String searchValue, int page, int rows);

    int updateNote(String customId, String note);
}
