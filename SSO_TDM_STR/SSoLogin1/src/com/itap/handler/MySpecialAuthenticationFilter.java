package com.itap.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.util.Assert;

public class MySpecialAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final String INTERCEPTOR_PROCESS_URL = "/sso/login";

	public MySpecialAuthenticationFilter() {
		super(INTERCEPTOR_PROCESS_URL);
	}

	public MySpecialAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		Assert.notNull(defaultFilterProcessesUrl, "Configuration error :: DefaultFilterProcessesUrl must be specified");
	}

	/**
	 * Method to do authentication of user
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {

		logger.info("Authenticating the user .....");

		Authentication authResult = null;
		try {

			String eid = request.getParameter("EID");

			if (StringUtils.isEmpty(eid)) {
				throw new PreAuthenticatedCredentialsNotFoundException("EID param not found in request.");
			}

			String credentials = "NA";
			PreAuthenticatedAuthenticationToken authRequest = new PreAuthenticatedAuthenticationToken(eid, credentials);
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
			authResult = getAuthenticationManager().authenticate(authRequest);
		} catch (AuthenticationException e) {
			unsuccessfulAuthentication(request, response, e);
		}
		return authResult;
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException,
			ServletException {

		if (logger.isDebugEnabled()) {
			logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
		}

		SecurityContextHolder.getContext().setAuthentication(authResult);

		getRememberMeServices().loginSuccess(request, response, authResult);

		// Fire event
		if (this.eventPublisher != null) {
			eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
		}

		getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}
}
