package com.lyf.service;

import java.sql.Timestamp;

public interface RecordService {

    String checkRecording(String userName);
    boolean startRecord(String key,String startTime,String index,String data);
    boolean handleRecord(Timestamp now,String dataIndex, String data); //String userName,String recordIndex,
    boolean suspendRecord();
    boolean recoverRecord();
    boolean endRecord(String dataIndex,String endTime,String data);//String userName,String recordIndex,
    public String handleLastData(String command,String userName);
    public void UnConnection();
}
