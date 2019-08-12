package com.cskaoyan.mapper;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.bean.COrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface COrderMapper {
    long countByExample(COrderExample example);

    int deleteByExample(COrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(COrder record);

    int insertSelective(COrder record);

    List<COrder> selectByExample(COrderExample example);

    COrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") COrder record, @Param("example") COrderExample example);

    int updateByExample(@Param("record") COrder record, @Param("example") COrderExample example);

    int updateByPrimaryKeySelective(COrder record);

    int updateByPrimaryKey(COrder record);

    COrder queryOrderById(@Param("id") String id);

    List<COrder> queryOrderList();


    List<COrder> selectPageCOrder();

    List<COrder> selectPageCOrderById(@Param("searchValueId") String searchValue);


    List<COrder> selectPageCOrderByCustom(@Param("searchValueCustom") String s);

    List<COrder> selectPageCOrderByProduct(@Param("searchValueProduct")String s);

    COrder selectOrderByPrimaryKey(@Param("orderId") String id);

}