package com.moc.common.page;

import java.util.List;

public class Pager {

	/** 总页数 */
	private Integer totalPages;
	/** 结果集 */
	private List list;
	/** 页号 默认1 */
	private Integer pageNum = 1;
	/** 每页大小 默认10 */
	private Integer pageSize = 10;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
