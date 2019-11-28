package com.mall.jiuzhenbao.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("curr_page")
    private int currentPage;
    
    @JsonProperty("total_page")
    private int totalPage;
    
    @JsonProperty("page_size")
    private int pageSize;
    
    @JsonProperty("has_prev")
    private boolean hasPrevious;
    
    @JsonProperty("has_next")
    private boolean hasNext;
    
    @JsonProperty("total_size")
    private long totalSize;
    
    public int getCurrentPage() {
        return currentPage;
    }

    public Pagination(){}
    
    public Pagination(int currentPage, int pageSize, int totalPage, boolean hasNext, boolean hasPrevious, long totalSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.totalSize = totalSize;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean hasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

}
