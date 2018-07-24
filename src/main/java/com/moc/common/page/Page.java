package com.moc.common.page;

import lombok.Data;

/**
 * 分页模型封装api
 * @author 
 * @version 2016-6-3
 */
@Data
public class Page {
	private Integer page;
	private Integer pageSize;

	public Page(Integer page, Integer size) {
        this.page = page;
        this.pageSize = page;

	}

}