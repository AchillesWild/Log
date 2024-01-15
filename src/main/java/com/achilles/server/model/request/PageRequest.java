package com.achilles.server.model.request;

public class PageRequest  implements java.io.Serializable {

    private static final long serialVersionUID = -4138241423214514057L;

    public static final int DEFAULT_PAGE_NO = 1;

    public static final int DEFAULT_PAGE_SIZE = 10;


    private Integer pageNo;

    private Integer pageSize;

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
}
