package com.moc.user.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UserEntity {

	private Long id;
	private String iconUrl;
	private String phone;
	private String weichatName;
	private Date regTime;
	private String weichatId;


}