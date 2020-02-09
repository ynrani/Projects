package com.itap.service;

import com.itap.exception.ServiceException;
import com.itap.model.DTO.ITAPUserDtlDTO;

public interface ITAPUserService {

	ITAPUserDtlDTO selectUserToEdit(String id, ITAPUserDtlDTO itapUserDtlDTO)
			throws ServiceException;

	String saveUserDetails(ITAPUserDtlDTO itapUserDtlDTO)
			throws ServiceException;

	String daleteUser(String id) throws ServiceException;

}
