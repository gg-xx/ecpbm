package com.xcu.pojo;

import lombok.Data;

@Data
public class Pager {
    private int curPage;
    private int perPageRows;
    private int rowCount;
    private int pageCount;
    public int getPageCount(){
        return (rowCount + perPageRows - 1) / perPageRows;
    }
    public int getFirstLimitParam(){
        return (this.curPage - 1) * this.perPageRows;
    }
}
