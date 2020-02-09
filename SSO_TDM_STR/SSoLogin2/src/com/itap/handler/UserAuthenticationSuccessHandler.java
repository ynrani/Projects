/*---------------------------------------------------------------------------------------
 * Object Name: UserAuthenticationSuccessHandler.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          12/06/15  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 
 *---------------------------------------------------------------------------------------*/

package com.itap.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.itap.constant.ITAPConstants;
import com.itap.util.JDBCPreparedStatementSelect;

/**
 * @author Seshadri Chowdary
 *
 */
public class UserAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private static Logger LOGGER = Logger
			.getLogger(UserAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String role = "";
	JDBCPreparedStatementSelect jd = null;

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		LOGGER.info(ITAPConstants.REMS_USER_AUTH_HANDLER
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_ONAUTH
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_ONAUTH_NO);
		handle(request, response, authentication);
		clearAuthenticationAttributes(request, authentication);
	}

	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		LOGGER.info(ITAPConstants.REMS_USER_AUTH_HANDLER
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_HANDLE
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_HANDLE_NO);
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Builds the target URL according to the logic defined in the main class
	 * Javadoc.
	 */
	protected String determineTargetUrl(Authentication authentication) {
		LOGGER.info(ITAPConstants.REMS_USER_AUTH_HANDLER
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_TAR_URL
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_TAR_URL_NO);
		boolean isUser = false;
		boolean isAdmin = false;
		boolean isTDMUser = false;
		boolean isTDMAdmin = false;

		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			role = grantedAuthority.getAuthority();
			if (role.equals(ITAPConstants.ROLE_USER)) {
				isUser = true;
				break;
			} else if (role.equals(ITAPConstants.ROLE_ADMIN)) {
				isAdmin = true;
				break;
			} else if (role.equals(ITAPConstants.ROLE_ENV_OWNR)) {
				isTDMUser = true;
				break;
			}
		}
		if (isUser) {
			return "/index2";
		} else if (isAdmin) {
			return "/index2";
		} else if (isTDMUser) {
			return "/index2";
		} else if (isTDMAdmin) {
			return "/index2";
		} else {
			LOGGER.error(ITAPConstants.REMS_USER_AUTH_HANDLER
					+ ITAPConstants.REMS_USER_AUTH_HANDLER_TAR_URL
					+ ITAPConstants.REMS_USER_AUTH_HANDLER_TAR_URL_RETN);
			return "/itaplogin?error=#";
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request,
			Authentication authentication) {
		String username = "", role_in_id = "";
		LOGGER.info(ITAPConstants.REMS_USER_AUTH_HANDLER
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_CLR_AUTH
				+ ITAPConstants.REMS_USER_AUTH_HANDLER_CLR_AUTH_NO);
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		User user = (User) authentication.getPrincipal();
		JDBCPreparedStatementSelect jd = new JDBCPreparedStatementSelect();
		try {
			String username_and_role = jd.selectRecordsFromTable(user
					.getUsername());
			StringTokenizer st = new StringTokenizer(username_and_role, "-");
			while (st.hasMoreTokens()) {
				username = st.nextToken();
				role_in_id = st.nextToken();
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		request.getSession()
				.setAttribute(ITAPConstants.SESSION_UNAME, username);
		request.getSession().setAttribute(ITAPConstants.SESSION_UID,
				user.getUsername());
		request.getSession().setAttribute(ITAPConstants.ROLE, role);
	}

}
