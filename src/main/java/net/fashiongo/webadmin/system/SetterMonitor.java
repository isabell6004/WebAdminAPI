package net.fashiongo.webadmin.system;

import net.fashiongo.webadmin.utility.Utility;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Aspect
@Component
public class SetterMonitor implements ThrowsAdvice {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 * @since 2018. 9. 13.
	 * @author Incheol Jung
	 * @param joinPoint
	 * @param e
	 * @throws Throwable 
	 * Desc : error log
	 */
	@AfterThrowing(pointcut = "execution(* net.fashiongo.webadmin.service..*(..)) " +
            "&& !execution(* net.fashiongo.webadmin.service.vendor.impl..*(..)) " +
			"&& !execution(* net.fashiongo.webadmin.service.BidService.*(..)) " +
			"&& !execution(* net.fashiongo.webadmin.service.WAPaymentService.*(..)) " +
			"&& !execution(* net.fashiongo.webadmin.service.MessageService.*(..)) " +
			"&& !execution(* net.fashiongo.webadmin.service.renewal.RenewalBuyerService.*(..)) " +
			"&& !execution(* net.fashiongo.webadmin.service.GnbService.*(..))", throwing="e")
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
	    
	    logger.warn(stuff + "\n method with arguments " + keyBuilder + " exception is: " + e.getMessage());
	    Utility.HttpResponse(exceptionMsg);
	}

    @AfterThrowing(pointcut = "execution(* net.fashiongo.webadmin.service.vendor.impl..*(..)) ", throwing="e")
    public void fashionGoNewApiExceptionLogging(JoinPoint joinPoint, Throwable e) {
        Signature signature = joinPoint.getSignature();
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(joinPoint.getTarget().getClass().getCanonicalName()).append(".");
        keyBuilder.append(signature.getName()).append(":");
        for (Object obj : joinPoint.getArgs())
            keyBuilder.append(obj).append(", ");

        logger.error(keyBuilder.toString() + ", {}", e.getMessage(), e);
    }
}
