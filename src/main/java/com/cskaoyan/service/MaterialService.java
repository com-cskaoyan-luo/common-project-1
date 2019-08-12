package com.cskaoyan.service;

import com.cskaoyan.bean.Material;
import com.cskaoyan.bean.MaterialExample;

import java.util.List;

public interface MaterialService {
    List<Material> findMaterialList(int page, int rows);

    Material getMaterialById(String materialId);

    List<Material> findAllMaterial();

    boolean insertMaterial(Material material);

    boolean updateMaterial(Material material, MaterialExample materialExample);

    boolean deleteMaterials(MaterialExample materialExample);

    List<Material> searchMaterialList(int page, int rows, MaterialExample materialExample);
}
