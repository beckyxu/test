package netease.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import netease.test.dao.IUserDao;
import netease.test.entity.User;
import netease.test.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {  
	
	@Autowired 
    private IUserDao userDao; 
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Override  
    public User getUserById(int userId) { 
        return userDao.selectByPrimaryKey(userId);  
    }

	@Override
	public int insertUser(User u) {
		int insert = userDao.insert(u);
		u.setId(4);
        userDao.updateByPrimaryKey(u);  	
		if(insert!=1)
			
				throw new RuntimeException("自定义异常");
			
		return insert;
	}  
  
    
    
}  