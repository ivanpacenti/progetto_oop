/**
 * 
 */
package it.univpm.progetto.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author ivan
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException e) {
	    String name = e.getParameterName();
	    LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
	    map.put("timestamp", new Date());
	    map.put("status", 400);
	    map.put("error", "Bad Request");
        map.put("message", name+" parameter is missing");
        map.put("path", "/search");
        

        return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	
	
	

}
