package com.moc.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moc.user.entity.UserEntity;
import com.moc.user.mapper.UserMapper;

@RestController
@RequestMapping("/user1")
public class UserController1 {
	
	@Autowired
	private UserMapper userMapper;
	
//	 @GetMapping("/listAllUser")
//	 public ResultInfo list() {
//		 List<UserEntity> us=userMapper.getAll();
//		 
//		// UserVO userVO = new UserVO();
//		 List<UserVO> ul = new ArrayList<UserVO>();
//         //BeanUtils.copyProperties(us, ul);
//       //  BeanUtils.co
//		 return ResultInfoUtil.success(us);
//	 }
	
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