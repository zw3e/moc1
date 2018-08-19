package com.moc.common.entity;

/**
 * 分页模型封装
 * @author Administrator
 * 
 */
public class Page {
	
	//起始位置
	private Integer start;
	
	//分页长度
	private Integer length = 1;
	
	//当前页数
	private Integer pageNo = 10;
	

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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
}