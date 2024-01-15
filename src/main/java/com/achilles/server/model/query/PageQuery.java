package com.achilles.server.model.query;

public class PageQuery implements java.io.Serializable {

    private static final long serialVersionUID = 6442078409696095649L;

    public static final int DEFAULT_PAGE_NO = 1;

    public static final int DEFAULT_PAGE_SIZE = 10;


    private Integer pageNo;

    private Integer pageSize;

    private Integer offSet;

    public Integer getPageNo() {
        if (pageNo == null) {
            return DEFAULT_PAGE_NO;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
        if (pageNo != null && pageSize != null) {
            return (pageNo - 1) * pageSize;
        }
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}
