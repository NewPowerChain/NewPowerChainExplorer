package com.msg;

import java.util.Map;

public class RespCountOfWeekTxType {

    Map<String, Map<String, Long>> countOfWeekTxType;

    public RespCountOfWeekTxType() {
    }

    public RespCountOfWeekTxType(Map<String, Map<String, Long>> countOfWeekTxType) {
        this.countOfWeekTxType = countOfWeekTxType;
    }

    public Map<String, Map<String, Long>> getCountOfWeekTxType() {
        return countOfWeekTxType;
    }
}
