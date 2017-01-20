package netease.test.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

public class ResponseUtil {

	private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	public static String getTemplateContent(String templateName, Map<String, Object> data,
			FreeMarkerConfigurer config) {
		StringWriter sw = new StringWriter();
		Template template = null;
		try {
			template = config.getConfiguration().getTemplate(templateName);
			template.process(data, sw);
		} catch (Exception e) {
			logger.error("getTemplateContent error", e);
		}
		return sw.toString();
	}

	public static boolean wMobile(HttpServletRequest httpRequest) {
		String userAgent = httpRequest.getHeader("User-Agent");
		if (StringUtils.isNotBlank(userAgent)) {
			if (userAgent.toLowerCase().contains("android") || userAgent.toLowerCase().contains("iphone")) {
				return true;
			}
		}
		return false;
	}

	public static void writeoutResult(HttpServletResponse response, String string) {
		if (string == null) {
			writeoutResult(response, "401<br>Illegal parameter");
			return;
		}
		try {
			response.setContentType("text/html; charset=GBK");
			response.setHeader("CACHE-CONTROL", "no-cache");
			response.getWriter().write(string);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeoutJSON(HttpServletResponse response, String string) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("CACHE-CONTROL", "no-cache");
			response.getWriter().write(string);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeHtml(HttpServletResponse httpResponse, String html) throws IOException {
		httpResponse.addHeader("Pragma", "no-cache");
		httpResponse.addHeader("Cache-Control", "no-cache");
		httpResponse.addDateHeader("Expries", 0L);
		httpResponse.addHeader("charset", "UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.addHeader("Content-Type", "text/html");
		httpResponse.getWriter().println(html);
	}

	public static void writeoutXml(HttpServletResponse response, String xml) {
		if (StringUtils.isBlank(xml)) {
			return;
		}
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("CACHE-CONTROL", "no-cache");
			response.getWriter().write(xml);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean setCookie(HttpServletResponse response, String domain, String cookieName,
			String cookieValue) {
		return setCookie(response, domain, cookieName, cookieValue, -1);
	}

	public static boolean setCookie(HttpServletResponse response, String domain, String cookieName, String cookieValue,
			int validTime) {
		try {
			Cookie newCookie = new Cookie(cookieName, cookieValue);
			newCookie.setPath("/");
			newCookie.setDomain(domain);
			newCookie.setMaxAge(validTime);
			response.addCookie(newCookie);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
