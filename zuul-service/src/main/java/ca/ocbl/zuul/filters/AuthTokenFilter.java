package ca.ocbl.zuul.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import ca.ocbl.common.Constants;
import ca.ocbl.user.entity.User;
import ca.ocbl.zuul.token.TokenManager;

/**
 * Filter APIs that requires token in HTTP header
 */
@Component
@RestController
public class AuthTokenFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Autowired
	private TokenManager tokenManager;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * if context path contains /rest/, do not apply filter
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String path = ctx.getRequest().getRequestURI();
		log.debug(String.format("get context path >>> %s", path));
		if (path.contains("/rest/")) {
			return false;
		}
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.debug(String.format("%s >>> %s", request.getMethod(), request.getRequestURI().toString()));

		String authorization = request.getHeader(Constants.AUTHORIZATION);
		log.debug("Getting authorization string={}", authorization);

		HttpServletResponse res = ctx.getResponse();
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.setContentType("application/json");

		String token = tokenManager.parseToken(authorization);
		if (token == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"message\":\"No token in request header\"}");
			return null;
		}

		User user = tokenManager.getUserByToken(token);

		if (user == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"message\":\"invalid token\"}");
			return null;
		} else {
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);

			// ctx.getResponse().getWriter().write("token is empty");
			// res.getWriter().write("{msg: 'token is empty'}");

			return null;
		}
	}
}
