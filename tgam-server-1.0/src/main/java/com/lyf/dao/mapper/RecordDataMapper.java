package com.lyf.dao.mapper;

import com.lyf.dao.domain.RecordData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordDataMapper {


    // 添加记录 id(自增主键) userId, rec
    void insertOneRecord(@Param("table") String table, RecordData recordData);

    Integer insertBatchRecordData(@Param("table") String table,List<RecordData> list);

    List<RecordData> getRecordData(@Param("table") String table,String infoId);//指定具体某次

    List<RecordData> getRecordByDate(@Param("table") String table,String infoId);// 模糊查询这一天所有

}
