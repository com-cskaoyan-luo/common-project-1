package com.cskaoyan.service;

import com.cskaoyan.bean.COrder;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {
    List<COrder> queryOrder(int page, int rows);

    int insertOrder(COrder cOrder);

    int updateAllOrder(COrder cOrder);

    int deleteBatch(List<String> strings);

    List<COrder> searchOrderById(String searchValue, int page, int rows);

    List<COrder> searchOrderByCustom(String searchValue, int page, int rows);

    int updateNote(String orderId,String note);

    List<COrder> searchOrderByProduct(String searchValue, int page, int rows);

    List<COrder> queryAllOrder();

    COrder queryOrderById(String id);
}
