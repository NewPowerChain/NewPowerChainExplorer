
package com.msg;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglinlin on 2017/11/9.
 */
public class PageDto<T> {
    private List<T> content = new ArrayList();
    private Boolean first;
    private Boolean last;
    private Integer number;
    private Integer numberOfElements;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;

    public PageDto() {

    }
//    public PageDto(List<T> list) {
//        this.content=list;
//    }
    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.number = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.size = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageDto{" +
                ", first=" + first +
                ", last=" + last +
                ", number=" + number +
                ", numberOfElements=" + numberOfElements +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", content=" + content+
                '}';
    }
}
