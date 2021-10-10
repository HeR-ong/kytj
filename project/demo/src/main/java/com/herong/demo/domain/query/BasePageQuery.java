package com.herong.demo.domain.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author herong22384
 * @date 2020/3/22 22:02
 */
public class BasePageQuery<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private int pages;
    private List<T> rows;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 是否开启分页查询
     * */
    public void startPage(Boolean startPage){
        if (startPage){
            PageHelper.startPage(pageNo, pageSize);
        }
    }

    public void compable(List<T> result){
        this.setRows(result);
        handleTotal(result);
    }

    /**
     * 处理分页总值
     * */
    public void handleTotal(List<T> result){
        PageInfo<T> pageInfo=new PageInfo<>(result);
        this.total = pageInfo.getTotal();
        this.pages = pageInfo.getPages();

    }
}
