package com.moc.user.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moc.common.ResultInfoUtil;
import com.moc.common.ResultVO;
import com.moc.user.VO.UserVO;
import com.moc.user.entity.UserEntity;
import com.moc.user.mapper.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	 @GetMapping("/listAllUser")
	 public ResultVO list() {
		 List<UserEntity> us=userMapper.getAll();
		 
		// UserVO userVO = new UserVO();
		 List<UserVO> ul = new ArrayList<UserVO>();
         //BeanUtils.copyProperties(us, ul);
       //  BeanUtils.co
		 return ResultInfoUtil.success(us);
	 }
	
    @RequestMapping("/add")
    public void save(UserEntity user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }
    
    
}