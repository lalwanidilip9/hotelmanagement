package online.hotelmanagement.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
/**
 * Global application error controller
 * @author Dilip
 *
 */
@Controller
public class GlobalApplicationErrorController implements ErrorController {

	/*@Autowired
	private Logger logger;*/
	
	/**
	 * Error Attributes in the Application
	 */
	private ErrorAttributes errorAttributes;

	/**
	 * Controller for the Error Controller
	 * 
	 * @param errorAttributes
	 */
	public GlobalApplicationErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	/**
	 * Supports the HTML Error View
	 * 
	 * @param request
	 * @return Model and view in case of error
	 */
	@RequestMapping(value = "/error", produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request ) {
		

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		ModelAndView model=null;
		if(statusCode == 500) {
			//logger.error("Error: " + getErrorAttributes(request, false));
			model = new ModelAndView("error/500");
		}
		if(statusCode == 404){			
			//logger.error("Error: " + getErrorAttributes(request, false));
			model = new ModelAndView("error/404");
		}		
		return model;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

	/**
	 * Supports other formats like JSON, XML
	 * 
	 * @param request
	 * @return ResponseEntity for Error
	 */
	@RequestMapping(value = "/error")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	/**
	 * Get HTTP status code
	 * @param request
	 * @return HTTP Status
	 */
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
				ex.getMessage();
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	/**
	 * Fetch trace parameter from HTTP request parameter present or not
	 * @param request
	 * @return true or false
	 */
	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equalsIgnoreCase(parameter);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
