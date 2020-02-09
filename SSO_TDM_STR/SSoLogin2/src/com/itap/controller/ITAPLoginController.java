/*---------------------------------------------------------------------------------------
 * Object Name: LoginController.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          26/02/16  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2015 
 *---------------------------------------------------------------------------------------*/

package com.itap.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itap.constant.ITAPConstants;

/**
 * @author Seshadri Chowdary
 * @version 1.0
 */

@Controller
public class ITAPLoginController {

	private static final Logger LOGGER = Logger
			.getLogger(ITAPLoginController.class);

	/**
	 * 
	 * @param error
	 * @param logout
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = ITAPConstants.ERROR, required = false) String error,
			@RequestParam(value = ITAPConstants.LOGOUT, required = false) String logout) {

		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGIN_CTLR_GET
				+ ITAPConstants.REMS_LOGIN_CTLR_YES + error);
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject(ITAPConstants.ERROR,
					ITAPConstants.INVALID_UNAME_PASS);
		} else if (StringUtils.isNotEmpty(error) && error.equals("#")) {
			model.addObject(ITAPConstants.ERROR, ITAPConstants.NEW_USER);
		}

		if (logout != null) {
			model.addObject(ITAPConstants.MSG, ITAPConstants.LOGOUT_SUCCESS);
		}
		model.addObject(ITAPConstants.MSG, ITAPConstants.SESSION_EXPIRED);
		model.setViewName(ITAPConstants.REMS_LOGIN_VIEW);
		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGIN_CTLR_GET
				+ ITAPConstants.REMS_LOGIN_CTLR_YES);
		return model;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/remsLogin")
	public String login(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGIN_CTLR_GET
				+ ITAPConstants.REMS_LOGIN_CTLR_NO);
		return "itaplogin";
	}

	@RequestMapping("/403page")
	public String accessDe(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGIN_CTLR_GET
				+ ITAPConstants.REMS_LOGIN_CTLR_NO);
		return "itapaccessDenied";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/index2")
	public String remsindex() {
		System.out.println("login index");
		return "index2";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(ITAPConstants.REMS_LOGIN_SESSIONEXP)
	public String sessionExp() {
		return ITAPConstants.REMS_LOGIN_VIEW;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(ITAPConstants.REMS_LOGIN_AUTHFAIL)
	public String authFail() {
		return ITAPConstants.REMS_LOGIN_VIEW;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(ITAPConstants.REMS_LOGIN_BACK)
	public String backToLogin() {
		return ITAPConstants.REMS_LOGIN_VIEW;
	}

	/**
	 * 
	 * @param model
	 * @param principal
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(ITAPConstants.REMS_LOOUT)
	public String logout(ModelMap model, Principal principal,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGOUT_CTLR_GET
				+ ITAPConstants.REMS_LOGOUT_CTLR_NO);
		String currentUser = null;
		if (null != (String) request.getSession().getAttribute(
				ITAPConstants.SESSION_UID)) {
			currentUser = (String) request.getSession().getAttribute(
					ITAPConstants.SESSION_UID);
		}
		if (null != currentUser) {
			request.getSession().invalidate();
			try {
				request.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info(ITAPConstants.REMS_LOGIN_CTLR
				+ ITAPConstants.REMS_LOGOUT_CTLR_GET
				+ ITAPConstants.REMS_LOGOUT_CTLR_NO);
		return ITAPConstants.REMS_LOGIN_VIEW;
	}

	@RequestMapping("/smortFoundry")
	public String smortFoundry() throws IOException {

		URL url = new URL("http://in-pnq-coe15:8080/REMS/");
		URLConnection uc = url.openConnection();
		String userpass = "demo" + ":" + "demo";
		String basicAuth = "Basic " + new String(userpass);
		uc.setRequestProperty("Authorization", basicAuth);
		InputStream in = uc.getInputStream();

		return "";
	}

}
