package com.moc.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moc.common.entity.Page;
import com.moc.common.mapper.CommonMapper;
import com.moc.common.utils.CommonUtil;



/**
 * 公共服务类
 * @author Administrator
 * 
 * @param <T>
 */
@Service
public class CommonServiceImpl<T> {
	/** 默认的Mapper映射 */
	protected CommonMapper<T> mapper;

	/**
	 * 初始化方法, 将子类的mapper赋值给当前对象的mapper
	 */
	protected void init() {
	}

	/**
	 * 获取数据列表
	 * @return 数据列表
	 */
	public List<T> list(Page page) {
		if (this.mapper == null) {
			init();
		}
		page = CommonUtil.initializationPage(page);
		return mapper.list(page);
	}

	/**
	 * 列出所有数据列表
	 * @param entity 查询条件
	 * @param page 分页条件(可为空)
	 * @return 所有数据列表
	 */
	public List<T> listByEntity(T entity, Page page) {
		if (this.mapper == null) {
			init();
		}
		page = CommonUtil.initializationPage(page);
		return mapper.listByEntity(entity, page);
	}

	/**
	 * 获取数据总数
	 * @return 数据总数
	 */
	public Integer count() {
		if (this.mapper == null) {
			init();
		}
		return mapper.count();
	}

	/**
	 * 根据entity获取数据总数
	 * @param entity 查询条件
	 * @return 数据总数
	 */
	public Integer countByEntity(T entity) {
		if (this.mapper == null) {
			init();
		}
		return mapper.countByEntity(entity);
	}

	/**
	 * 根据编号获取数据
	 * @param id 数据编号
	 * @return 数据详情
	 */
	public T get(Serializable id) {
		if (this.mapper == null) {
			init();
		}
		return mapper.get(id);
	}

	/**
	 * 根据数据对象的属性获取指定数据
	 * @param entity 对象形式的参数
	 * @return 指定数据
	 */
	public T getByEntity(T entity) {
		if (this.mapper == null) {
			init();
		}
		return mapper.getByEntity(entity);
	}
	
	/**
	 * 根据数据对象的属性获取指定数据
	 * @param entity 对象形式的参数
	 * @return 指定数据
	 */
	public T getByEntityId(T entity) {
		if (this.mapper == null) {
			init();
		}
		return mapper.getByEntityId(entity);
	}

	/**
	 * 获取排序码最大值
	 * @return 排序码最大值
	 */
	public Integer getMaxSortNo() {
		if (this.mapper == null) {
			init();
		}
		return mapper.getMaxSortNo();
	}

	/**
	 * 数据判重
	 * @param entity
	 * @return Integer
	 */
	public Integer judgeDuplicate(T entity) {
		if (this.mapper == null) {
			init();
		}
		return mapper.judgeDuplicate(entity);
	}

	/**
	 * 保存指定数据到数据库
	 * @param entity 待保存数据
	 */
	public void save(T entity) {
		if (this.mapper == null) {
			init();
		}
		mapper.save(entity);
	}

	/**
	 * 批量保存数据
	 * @param entitys 待保存的批量数据
	 */
	public void saveAll(List<T> entitys) {
		if (this.mapper == null) {
			init();
		}
		mapper.saveAll(entitys);
	}

	/**
	 * 更新指定数据
	 * @param entity 待更新数据
	 */
	public void update(T entity) {
		if (this.mapper == null) {
			init();
		}
		mapper.update(entity);
	}

	/**
	 * 批量更新数据
	 * @param entitys 待更新的批量数据
	 */
	public void updateAll(List<T> entitys) {
		if (this.mapper == null) {
			init();
		}
		mapper.updateAll(entitys);
	}

	/**
	 * 根据ID删除指定数据
	 * @param id 待删除的ID
	 */
	public void remove(Serializable id) {
		if (this.mapper == null) {
			init();
		}
		mapper.remove(id);
	}

	/**
	 * 根据ID删除指定数据
	 * @param entity 待删除的对象(包含删除的条件)
	 */
	public void removeByEntity(T entity) {
		if (this.mapper == null) {
			init();
		}
		mapper.removeByEntity(entity);
	}

	/**
	 * 通过ID集合批量删除数据
	 * @param ids 待删除的数据ID集合
	 */
	public void removeAll(List<Integer> idList) {
		if (this.mapper == null) {
			init();
		}
		mapper.removeAll(idList);
	}

	/**
	 * 通过ID集合批量删除数据
	 * @param ids 待删除的数据ID集合
	 */
	public void softRemoveAll(List<Integer> idList) {
		if (this.mapper == null) {
			init();
		}
		mapper.softRemoveAll(idList);
	}
	
	/**
	 * 批量删除数据
	 * @param entitys 待删除的批量数据
	 */
	public void removeAllByEntity(List<Integer> idList) {
		if (this.mapper == null) {
			init();
		}
		mapper.removeAllByEntity(idList);
	}
}