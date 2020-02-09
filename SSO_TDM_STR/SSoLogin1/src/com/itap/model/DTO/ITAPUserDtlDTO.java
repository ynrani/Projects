/*---------------------------------------------------------------------------------------
 * Object Name: ITAPUserAppAccessDTO.Java
 * 
 * Modification Block:
 * --------------------------------------------------------------------------------------
 * S.No. Name                Date      Bug Fix no. Desc
 * --------------------------------------------------------------------------------------
 * 1     Seshadri Chowdary          26/02/16  NA          Created
 * --------------------------------------------------------------------------------------
 *
 * Copyright: 2016 
 *---------------------------------------------------------------------------------------*/
package com.itap.model.DTO;

public class ITAPUserDtlDTO
{
	private String userId;
	private String actionBy;
	private String enabled;
	private String passWord;
	private String userAccess;
	private String userEmail;
	private String userName;
	private String userPh;
	private String userSts;
	private String userType;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}

	public String getUserAccess() {
		return userAccess;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserPh(String userPh) {
		this.userPh = userPh;
	}

	public String getUserPh() {
		return userPh;
	}

	public void setUserSts(String userSts) {
		this.userSts = userSts;
	}

	public String getUserSts() {
		return userSts;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

}