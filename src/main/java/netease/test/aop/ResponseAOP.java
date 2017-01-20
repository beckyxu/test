package netease.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.netease.mail.walter.common.JMap;
import com.netease.mail.walter.common.ObjectUtil;

/**
 * 用来处理controller的异常信息返回
 * 
 * @author Walter
 *
 */
@Component
@Aspect
@Order(value = 10)
public class ResponseAOP {

	@Pointcut("execution(* netease.test.controller.*.*(..))")
	private void response() {
	}

	@Around("response()")
	private Object response(ProceedingJoinPoint pjp) throws Throwable {
		Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
		try {
			logger.info(pjp.toShortString() + " start>>>>>>>>>>>>>>>>, param:" + ObjectUtil.toString(pjp.getArgs()));
			Object proceed = pjp.proceed();
			logger.info(ObjectUtil.toString(proceed));
			logger.info("proceed :"+proceed);
			logger.info(pjp.toShortString() + " finish<<<<<<<<<<<<<<<, output:" + ObjectUtil.toString(proceed));
			return proceed;
		} catch (Throwable e) {
			return errorOutput("系统出错：" + e.getMessage(), pjp, logger, e);
		}
	}

	private JMap errorOutput(String msg, ProceedingJoinPoint pjp, Logger logger, Throwable e) {
		JMap output = new JMap();
		output.put("code", -200);
		output.put("msg", msg);
		logger.error(pjp.toShortString() + " error<<<<<<<<<<<<<<<, output:" + output, e);
		return output;
	}
}
