package com.moc.cumtomer.mapper;

import java.util.List;

import com.moc.cumtomer.entity.UserEntity;

public interface UserMapper {

	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);
}
