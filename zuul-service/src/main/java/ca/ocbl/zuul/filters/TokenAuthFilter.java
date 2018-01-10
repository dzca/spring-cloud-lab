package ca.ocbl.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Filter APIs that requires token in HTTP header
 * /api/* - API exposed in public domain require token authentication
 * /rest/* - API exposed internally do not require token authentication 
 */
@Component
@RestController
public class TokenAuthFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(TokenAuthFilter.class);
    
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        
        Object accessToken = request.getParameter("token");
        
        HttpServletResponse res = ctx.getResponse();
    	res.addHeader("Access-Control-Allow-Origin", "*");
    	res.setContentType("application/json");
    	
//        if(accessToken == null) {
//            log.warn("token is empty");
//            
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            
//            try {
//                //ctx.getResponse().getWriter().write("token is empty");
//            	res.getWriter().write("{msg: 'token is empty'}");
//                //application/json
//            }catch (Exception e){}
//            
//            return null;
//            
//            //return null;
//        }
        log.info("ok");
        return null;
    }
}
