package com.itap.dao;

import javax.persistence.EntityManager;

import com.itap.exception.DAOException;
import com.itap.model.DO.ITAPUserDtlDO;

public interface ITAPUserDAO {

	public ITAPUserDtlDO selectUserToEdit(String id, EntityManager managerUser)
			throws DAOException;

	public String saveUserDetails(
			ITAPUserDtlDO convertFromITAPUserDtlDTOToITAPUserDtlDO,
			EntityManager managerUser) throws DAOException;

	public String daleteUser(String id, EntityManager managerUser)
			throws DAOException;

}
