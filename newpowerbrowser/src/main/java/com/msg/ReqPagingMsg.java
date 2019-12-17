package com.msg;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * PC端分页请求基础类
 * Created by wanglinlin on 2016/8/23.
 */
public class ReqPagingMsg {
    public Integer page = 0;
    public Integer size = 10;
    private Integer start = 0;
    private Integer length = 10;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    private Integer getPageNum() {
        return start / length;
    }
    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
    public PageRequest getPageRequest() {
        return new PageRequest(getPage(), getSize() , Sort.Direction.DESC,"id");
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override

    public String toString() {
        return "ReqPagingMsg{" +
                "start=" + start +
                ", length=" + length +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
