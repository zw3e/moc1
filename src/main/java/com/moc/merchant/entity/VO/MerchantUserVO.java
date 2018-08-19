package com.moc.merchant.entity.VO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MerchantUserVO {

	private Long id;
	private String phone;
	private String userName;
	
	@JsonIgnore
	private String password;
	private String sex;
	private Date createTime;
	private Date updateTime;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

	
	
	
	


	

	
	
	
	
	
}