package net.fashiongo.webadmin.system;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import net.fashiongo.webadmin.utility.JsonResponse;


@Aspect
@Component
public class SetterMonitor implements ThrowsAdvice {
	protected final Logger logger = LogManager.getLogger();
	LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
	
	/**
	 * 
	 * @since 2018. 9. 13.
	 * @author Incheol Jung
	 * @param joinPoint
	 * @throws Throwable 
	 * Desc : performance log
	 */
	@Around("execution(* net.fashiongo.webadmin.service..*(..))")
	public Object callMethodAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object retVal = joinPoint.proceed();
		long elapsed = System.currentTimeMillis() - start;

		Signature sig = joinPoint.getSignature();

		logger.info("info : "+sig.getDeclaringTypeName() + "#" + sig.getName() + "\t" + elapsed + " ms");
		
		return retVal;
	}
	
	/**
	 * 
	 * @since 2018. 9. 13.
	 * @author Incheol Jung
	 * @param joinPoint
	 * @param e
	 * @throws Throwable 
	 * Desc : error log
	 */
	@AfterThrowing(pointcut = "execution(* net.fashiongo.webadmin.service..*(..))" , throwing="e")
	public void callMethodException(JoinPoint joinPoint, Throwable e) throws Throwable {

		Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
//	    String arguments = Arrays.toString(joinPoint.getArgs());
	    StringBuilder keyBuilder = new StringBuilder();
	    keyBuilder.append(methodName + ":");
	    ObjectMapper oMapper = new ObjectMapper();
	    String exceptionMsg = e.getMessage();
	    
	    for (Object obj : joinPoint.getArgs()) {
	    	if (obj instanceof HttpServletRequest) {
	    		HttpServletRequest req = (HttpServletRequest) obj;
			}else if(obj instanceof MultipartFile) {
				MultipartFile file = (MultipartFile) obj;
				keyBuilder.append("{fileName : " + file.getOriginalFilename() + "}");
			}else {
				try {
					Map<String, Object> map = oMapper.convertValue(obj, Map.class);
					keyBuilder.append("{" + map.toString() + "}");
				}catch(Exception ex) {
					if(obj != null) keyBuilder.append(obj.toString());
				}
			}
	    }
	    
	    logger.error(stuff + "\n method with arguments " + keyBuilder + " exception is: " + e.getMessage());
	    HttpResponse(exceptionMsg);
	}
	
	/**
	 * 
	 * @since 2018. 9. 13.
	 * @author Incheol Jung
	 * @param exceptionMsg
	 * @throws Throwable 
	 * Desc : return error response
	 */
	public void HttpResponse(String exceptionMsg) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	    
		JsonResponse<String> res = new JsonResponse<String>();
		res.setSuccess(false);
		res.setCode(-1);
		res.setMessage(exceptionMsg);
		res.setData(null);
		
	    ObjectMapper om = new ObjectMapper();
		String returnStr = om.writeValueAsString(res);
		OutputStream ostr = response.getOutputStream();
		ostr.write(returnStr.getBytes());
		ostr.flush();
		ostr.close();
	}
}