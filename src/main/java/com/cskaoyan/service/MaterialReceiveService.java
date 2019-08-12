package com.cskaoyan.service;

import com.cskaoyan.bean.MaterialReceive;
import com.cskaoyan.bean.MaterialReceiveExample;

import java.util.List;

public interface MaterialReceiveService {
    List<MaterialReceive> findMaterialReceiveList(int page, int rows);

    boolean insertMaterialReceive(MaterialReceive materialReceive);

    boolean updateMaterialReceive(MaterialReceive materialReceive, MaterialReceiveExample materialReceiveExample);

    boolean deleteMaterialReceives(MaterialReceiveExample materialReceiveExample);

    List<MaterialReceive> searchMaterialReceiveList(int page, int rows, MaterialReceiveExample materialReceiveExample);
}
