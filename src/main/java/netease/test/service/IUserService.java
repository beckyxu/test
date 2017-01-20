package netease.test.service;

import netease.test.entity.User;

public interface IUserService {
	
	  public User getUserById(int userId);  
	  
	  public int insertUser(User u);
	  
}
