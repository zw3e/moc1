package com.moc.common.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moc.common.entity.Page;



/**
 * 公共的Dao接口
 * @author Administrator
 * 
 * @param <T>
 */
public interface CommonMapper<T> {
	/**
	 * 列出所有数据列表
	 * @param page 分页条件(可为空)
	 * @return 所有数据列表
	 */
	List<T> list(@Param("page") Page page);

	/**
	 * 列出所有数据列表
	 * @param entity 查询条件
	 * @param page 分页条件(可为空)
	 * @return 所有数据列表
	 */
	List<T> listByEntity(@Param("entity") T entity, @Param("page") Page page);

	/**
	 * 获取数据总数
	 * @return 数据总数
	 */
	Integer count();

	/**
	 * 根据entity获取数据总数
	 * @param entity 查询条件
	 * @return 数据总数
	 */
	Integer countByEntity(@Param("entity") T entity);

	/**
	 * 根据编号获取数据
	 * @param id 数据编号
	 * @return 数据详情
	 */
	T get(@Param("id") Serializable id);

	/**
	 * 根据数据对象的属性获取指定数据
	 * @param entity 对象形式的参数
	 * @return 指定数据
	 */
	T getByEntity(@Param("entity") T entity);
	
	/**
	 * 根据数据对象的属性获取指定数据
	 * @param entity 对象形式的参数
	 * @return 指定数据
	 */
	T getByEntityId(@Param("entity") T entity);

	/**
	 * 获取排序码最大值
	 * @return 排序码最大值
	 */
	Integer getMaxSortNo();

	/**
	 * 数据判重
	 * @param entity
	 * @return Integer
	 */
	Integer judgeDuplicate(@Param("entity") T entity);

	/**
	 * 保存指定数据到数据库
	 * @param entity 待保存数据
	 */
	void save(@Param("entity") T entity);

	/**
	 * 批量保存数据
	 * @param entitys 待保存的批量数据
	 */
	void saveAll(@Param("entitys") List<T> entitys);

	/**
	 * 更新指定数据
	 * @param entity 待更新数据
	 */
	void update(@Param("entity") T entity);

	/**
	 * 批量更新数据
	 * @param entitys 待更新的批量数据
	 */
	void updateAll(@Param("entitys") List<T> entitys);

	/**
	 * 根据ID删除指定数据
	 * @param id 待删除的ID
	 */
	void remove(@Param("id") Serializable id);

	/**
	 * 根据ID删除指定数据
	 * @param entity 待删除的对象(包含删除的条件)
	 */
	void removeByEntity(@Param("entity") T entity);

	/**
	 * 通过ID集合批量删除数据
	 * @param idList 待删除的数据ID集合
	 */
	void removeAll(@Param("idList") List<Integer> idList);
	
	/**
	 * 通过ID集合批量软删除数据
	 * @param idList 待删除的数据ID集合
	 */
	void softRemoveAll(@Param("idList") List<Integer> idList);

	/**
	 * 批量删除数据
	 * @param idList 待删除的批量数据
	 */
	void removeAllByEntity(@Param("entitys") List<Integer> idList);
}