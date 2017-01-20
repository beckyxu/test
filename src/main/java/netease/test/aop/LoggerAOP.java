package netease.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.netease.mail.walter.common.ObjectUtil;


/**
 * 日志打印器
 * 
 * @author Walter
 *
 */
@Component
@Aspect
@Order(value = 10)
public class LoggerAOP {

	@Pointcut("execution(* netease.test.service.impl.*.*(..))")
	private void log() {
	}

	@Around("log()")
	private Object log(ProceedingJoinPoint pjp) throws Throwable {
		return doLog(pjp);
	}

/**
	 * 非controller的日志
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	private Object doLog(ProceedingJoinPoint pjp) throws Throwable {
		Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
		long startTime = System.currentTimeMillis();
		try {
			//Arrays.toString(pjp.getArgs())
			Object proceed = pjp.proceed();
			logger.info(pjp.toShortString() + " --------finish, param:" + ObjectUtil.toString(pjp.getArgs()) + ", result:"
					+ ObjectUtil.toString(proceed) + ", use time:" + (System.currentTimeMillis() - startTime) + "ms");
			return proceed;
		} catch (Exception e) {
			if (pjp.getArgs() != null) {
				logger.error(pjp.toShortString() + " error, param:" + ObjectUtil.toString(pjp.getArgs()) + ", use time:"
						+ (System.currentTimeMillis() - startTime) + "ms", e);
			} else {
				logger.error(pjp.toShortString() + " error, param:" + pjp.getArgs() + ", use time:"
						+ (System.currentTimeMillis() - startTime) + "ms", e);
			}
			throw e;
		}
	}
}
