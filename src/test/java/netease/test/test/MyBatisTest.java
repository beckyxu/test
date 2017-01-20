package netease.test.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import netease.test.dao.IEntityDao;
import netease.test.dao.IUserDao;
import netease.test.entity.PersonEntity;
import netease.test.entity.User;
import netease.test.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
    @ContextConfiguration(name = "parent", locations = "classpath:META-INF/config/spring/**/*.xml"),
    @ContextConfiguration(name = "child", locations = "classpath:META-INF/web-config/spring/**/*.xml") })
public class MyBatisTest {
	 private static Logger logger = Logger.getLogger(MyBatisTest.class);  

	    @Autowired 
	    private IUserDao userdao;  
	    @Autowired
	    private IEntityDao entitydao;
	    @Autowired
	    private IUserService userservice; 
	    
	    @Test  
	    public void testSelectByPrimaryKey() {  
	        User user = userdao.selectByPrimaryKey(1);  	       
	        logger.info("测试查找功能--------"+user.getUserName());  
	    } 
	    
	    @Test  
	    public void testInsert() {  
	    	User u = new User();
	    	u.setAge(10);
	    	u.setPassword("111");
	    	u.setUserName("哈哈哈");
	        int n = userdao.insert(u);  	       
	        logger.info("测试插入功能--------"+n);  
	    }  
	    
	    @Test  
	    public void testDeleteByPrimaryKey() {  	    	
	        int n = userdao.deleteByPrimaryKey(3);  	       
	        logger.info("测试删除功能--------"+n);  
	    } 
	    
	    @Test  
	    public void testInsertSelective() {  	    	
	    	User u = new User();
	    	u.setAge(20);
	    	int n1 = userdao.insertSelective(u);  
	    	u.setPassword("221");
	    	int n2 = userdao.insertSelective(u);  
	    	u.setUserName("哈哈哈SS");
	        int n3 = userdao.insertSelective(u);  	       
	        logger.info("测试按条件插入功能--------"+n1+" "+n2+" "+n3); 
	    }  
	    
	    @Test  
	    public void testUpdateByPrimaryKey() {  	    	
	    	User u = new User();
	    	u.setId(4);
	    	u.setAge(30);
	    	u.setPassword("1111");
	    	u.setUserName("哈哈哈S");
	        int n3 = userdao.updateByPrimaryKey(u);  	       
	        logger.info("测试更新功能--------"+n3); 
	    }  
	    
	    @Test  
	    public void testUpdateByPrimaryKeySelective() {  	    	
	    	User u = new User();
	    	u.setId(6);
	        u.setUserName("哈哈哈");
	        int n1 = userdao.updateByPrimaryKeySelective(u); 
	        u.setId(5);
	        u.setAge(1);
	    	u.setPassword("圣诞节呢");
	    	u.setUserName(null);
	    	int n2 = userdao.updateByPrimaryKeySelective(u);
	    	u.setId(4);
	    	u.setUserName(null);
	    	u.setPassword("hank呢");
	        int n3 = userdao.updateByPrimaryKeySelective(u);  	       
	        logger.info("测试按条件插入功能--------"+n1+" "+n2+" "+n3); 
	    }  
	    
	    @Test  
	    public void testSelectByPage() {  	    	
	    	Map<String,Object> map = new HashMap<>();
	    	map.put("userName", "%呢%");
	    	map.put("age", "%1%");
	    	map.put("start", 0);
	    	map.put("size", 10);
			List<User> u =userdao.selectByPage(map);
			if(u!=null) {
				for(User user:u) {

			        logger.info(user.getUserName()); 
				}
			}
			
	        logger.info("测试分页查询功能--------"+u); 
	    }  
	    
	    @Test  
	    public void testConnectOtherTable() {  
	    	List<User> connectOtherTable = userdao.connectOtherTable();
	    	if(connectOtherTable!=null) {
				for(User user:connectOtherTable) {

			        logger.info(user.getUserName()); 
				}
			}
			
	        logger.info("测试多表连接功能--------"+connectOtherTable); 
	    } 
	    
	    @Test  
	    public void testTransaction() {  
	    	User u = new User();
	    	u.setAge(10);
	    	u.setPassword("111");
	    	u.setUserName("哈哈哈");
	        int n = userservice.insertUser(u);  	
			 if(n==1)
				 throw new RuntimeException("运行时异常");
	        logger.info("测试事务回滚--------"+n); 
	    } 
	    
	    @Test  
	    public void testSelectEntityByPrimaryKey() {  
	        PersonEntity entity = entitydao.selectByPrimaryKey(0);  	       
	        logger.info("测试另外一个类--------"+entity);  
	    } 
	    
}
