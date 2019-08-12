package com.cskaoyan.mapper;

import com.cskaoyan.bean.Work;
import com.cskaoyan.bean.WorkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkMapper {
    long countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(String workId);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(String workId);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);

    List<Work> selectPageWork();

    List<Work> selectPageWorkById(@Param("searchValueId") String searchValue);

    List<Work> selectPageWorkByProduct(@Param("searchValueProduct")String s);

    List<Work> selectPageWorkByDevice(@Param("searchValueDevice")String s);

    List<Work> selectPageWorkByProcess(@Param("searchValueProcess")String s);

    Work selectWorkByPrimaryKey(@Param("workId") String id);
}