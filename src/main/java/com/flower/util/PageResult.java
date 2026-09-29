package com.flower.util;

import java.util.List;

public class PageResult<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<T> list;

    public PageResult(int pageNum, int pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = (int) Math.ceil((double) total / pageSize);
        this.list = list;
    }

    public int getPageNum() { return pageNum; }
    public int getPageSize() { return pageSize; }
    public long getTotal() { return total; }
    public int getPages() { return pages; }
    public List<T> getList() { return list; }
    public int getPrePage() { return pageNum - 1; }
    public int getNextPage() { return pageNum + 1; }
    public boolean isHasPreviousPage() { return pageNum > 1; }
    public boolean isHasNextPage() { return pageNum < pages; }
    public boolean isFirstPage() { return pageNum == 1; }
    public boolean isLastPage() { return pageNum == pages; }
}