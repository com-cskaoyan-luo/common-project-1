package com.cskaoyan.service;

import com.cskaoyan.bean.MaterialConsume;
import com.cskaoyan.bean.MaterialConsumeExample;

import java.util.List;

public interface MaterialConsumeService {
    List<MaterialConsume> findMaterialConsumeList(int page, int rows);

    boolean insertMaterialConsume(MaterialConsume materialConsume);

    boolean updateMaterialConsume(MaterialConsume materialConsume, MaterialConsumeExample materialConsumeExample);

    boolean deleteMaterialConsumes(MaterialConsumeExample materialConsumeExample);

    List<MaterialConsume> searchMaterialConsumeByExample(int page, int rows, MaterialConsumeExample materialConsumeExample);
}
