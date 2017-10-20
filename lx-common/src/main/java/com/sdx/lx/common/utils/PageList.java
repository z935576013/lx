package com.sdx.lx.common.utils;

import java.util.List;

/**
 * 分页列表
 * @author lifei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PageList<T> extends Result {

    private static final long serialVersionUID = -9036631420630379164L;
    
    
    /** 分页 */
    protected Page            page;

    /** 数据列表 */
    protected List<T>         list;

    /**
     * 构造函数
     */
    public PageList() {
        super();
    }

    /**
     * 构造函数
     * @param page 分页
     * @param list 数据列表
     */
    public PageList(Page page, List<T> list) {
        super();
        this.page = page;
        this.list = list;
    }

    /**
     * 获取分页
     * @return 分页
     */
    public Page getPage() {
        return page;
    }

    /**
     * 设置分页
     * @param page 分页
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 获取数据列表
     * @return 数据列表
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置数据列表
     * @param list 数据列表
     */
    public void setList(List<T> list) {
        this.list = list;
    }

}
