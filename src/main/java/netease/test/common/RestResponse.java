package netease.test.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * 返回结果
 * @author  xujin
 *
 */
public class RestResponse implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4670096981339867713L;
    
    private String message;

    private int code;

    private Object data;
    
    private Object error;

    public static final String CONTENT_TYPE_JSON="application/json;charset=UTF-8";
    
    public static final Logger logger = LoggerFactory.getLogger(RestResponse.class);

    private RestResponse() {
        this(HttpStatus.SC_OK, "", null, null);
    }

    private RestResponse(int code) {
        this(code, "", null, null);
    }
    
    private RestResponse(Object data) {
        this(HttpStatus.SC_OK, "", data, null);
    }
    
//    private AjaxResponseBody(Throwable e, String message) {
//        this(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, null,  e);
//    }
    
    private RestResponse( int code, String msg, Object data, Error error) {
        super();
        if (code == 0) {
            code = HttpStatus.SC_OK;
        }
        this.code = code;
        this.message = msg;
        this.data = data;
        this.error = error;
        //https://wiki.mail.netease.com/pages/viewpage.action?pageId=17139800
        //返回的对象的code和http返回的status code一致
        setResponeHttpStatusCode(code);
    }

    private void setResponeHttpStatusCode(int code) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            response.setStatus(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public String getMessage() {
        return message;
    }


    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    
    public Object getError() {
        return error;
    }


    public String wrapJsonData(String jsonData) {
        
        String format="{\"message\": \"%s\", \"code\": %d, \"data\": %s}";
        
        return String.format(format, this.message == null? "" : this.message, this.code, jsonData);
    }
    
    public static RestResponse ok() {
        return new RestResponse(HttpStatus.SC_OK);
    }
    
  

    
    public static RestResponse updated() {
        return new RestResponse(HttpStatus.SC_ACCEPTED);
    }
    
    public static RestResponse deleted() {
        return new RestResponse(HttpStatus.SC_NO_CONTENT);
    }
    
    public static RestResponse notFound(String message) {
        return error(HttpStatus.SC_NOT_FOUND, message);
    }
    
    public static RestResponse wrongParam(String message) {
        return error(HttpStatus.SC_BAD_REQUEST, message);
    }
    
    public static RestResponse error(String message) {
        return new RestResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, null, null);
    }
    
    public static RestResponse error(int code, String message) {
        return new RestResponse(code, message, null, null);
    }

    public static void main(String[] args) {
        System.out.println(new RestResponse().wrapJsonData("[0,1]"));
    }
}
