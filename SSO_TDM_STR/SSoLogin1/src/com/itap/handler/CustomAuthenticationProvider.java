package com.itap.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class CustomAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedUserDetailsService = null;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if (!supports(authentication.getClass())) {
			return null;
		}

		if (authentication.getPrincipal() == null) {
			return null;
		}

		UserDetails ud = preAuthenticatedUserDetailsService.loadUserDetails((PreAuthenticatedAuthenticationToken) authentication);

		PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(ud, authentication.getCredentials(), ud.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	/**
	 * @return the preAuthenticatedUserDetailsService
	 */
	public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> getPreAuthenticatedUserDetailsService() {
		return preAuthenticatedUserDetailsService;
	}

	/**
	 * @param preAuthenticatedUserDetailsService
	 *            the preAuthenticatedUserDetailsService to set
	 */
	public void setPreAuthenticatedUserDetailsService(
			AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> preAuthenticatedUserDetailsService) {
		this.preAuthenticatedUserDetailsService = preAuthenticatedUserDetailsService;
	}
}