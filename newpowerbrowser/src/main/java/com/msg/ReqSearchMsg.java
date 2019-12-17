package com.msg;

public class ReqSearchMsg {
    String searchCondition;

    public ReqSearchMsg() {
    }

    public ReqSearchMsg(String searchCondition) {
        this.searchCondition = searchCondition;
    }


    public String getSearchCondition() {
        return searchCondition;
    }
}
