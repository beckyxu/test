package netease.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.mail.walter.common.JMap;

import netease.test.common.KeyValues;
import netease.test.entity.User;
import netease.test.service.IUserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(value ="/addUser.do", method = RequestMethod.GET)
	@ResponseBody
	public JMap index(@RequestParam("age") Integer age,@RequestParam("password") String password,@RequestParam("userName") String userName) {
		logger.debug("[opt:addUser]");
	    User u = new User();
	    u.setAge(age);
	    u.setPassword(password);
	    u.setUserName(userName);
	    userService.insertUser(u);
	    JMap jResult = new JMap();
		jResult.put(KeyValues.KEY_CODE, KeyValues.VAL_RET_CODE_SUCCESS);
		jResult.put(KeyValues.KEY_DATA, u);
	    return jResult;
	}

}
