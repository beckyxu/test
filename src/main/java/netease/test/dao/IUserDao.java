package netease.test.dao;

import java.util.List;
import java.util.Map;

import netease.test.entity.User;

public interface IUserDao {

	User selectByPrimaryKey(Integer id);
	
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByPage(Map<String,Object> map);
    
    List<User> connectOtherTable();
}
