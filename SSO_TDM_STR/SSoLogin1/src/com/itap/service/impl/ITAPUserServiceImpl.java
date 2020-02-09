/*---------------------------------------------------------------------------------------
 * Object Name: ITAPUserController.Java
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

package com.itap.service.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itap.constant.ITAPConstants;
import com.itap.dao.ITAPUserDAO;
import com.itap.exception.DAOException;
import com.itap.exception.ServiceException;
import com.itap.model.DO.ITAPUserDtlDO;
import com.itap.model.DTO.ITAPUserDtlDTO;
import com.itap.model.mapper.ITAPUserDtlMapper;
import com.itap.service.ITAPUserService;

@Component
@Service("itapUserService")
@Transactional(propagation = Propagation.REQUIRED)
public class ITAPUserServiceImpl extends ITAPBaseServiceImpl implements
		ITAPUserService {

	@Autowired
	ITAPUserDAO itapUserDAO;

	@Autowired
	ITAPUserDtlMapper itapUserDtlMapper;

	@Override
	public ITAPUserDtlDTO selectUserToEdit(String id,
			ITAPUserDtlDTO itapUserDtlDTO) throws ServiceException {
		try {
			EntityManager managerUser = openUserEntityManager();
			itapUserDtlDTO = itapUserDtlMapper
					.convertFromITAPUserDtlDOToITAPUserDtlDTO(
							itapUserDAO.selectUserToEdit(id, managerUser),
							itapUserDtlDTO);
			closeUserEntityManager(managerUser);
			return itapUserDtlDTO;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String saveUserDetails(ITAPUserDtlDTO itapUserDtlDTO)
			throws ServiceException {
		try {
			EntityManager managerUser = openUserEntityManager();
			String sts = itapUserDAO.saveUserDetails(itapUserDtlMapper
					.convertFromITAPUserDtlDTOToITAPUserDtlDO(itapUserDtlDTO,
							new ITAPUserDtlDO()), managerUser);
			closeUserEntityManager(managerUser);
			return sts;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String daleteUser(String id) throws ServiceException {
		try {
			EntityManager managerUser = openUserEntityManager();
			String str = itapUserDAO.daleteUser(id, managerUser);
			closeUserEntityManager(managerUser);
			return str;
		} catch (NullPointerException nullPointerEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (DAOException daoEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(daoEx.getErrorCode(), daoEx);
		} catch (Exception otherEx) {
			// releaseEntityMgrForRollback(entityManager);
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

}
