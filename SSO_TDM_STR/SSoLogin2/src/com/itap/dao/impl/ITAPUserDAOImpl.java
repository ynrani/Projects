package com.itap.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.itap.constant.ITAPConstants;
import com.itap.dao.ITAPUserDAO;
import com.itap.exception.DAOException;
import com.itap.model.DO.ITAPUserDtlDO;

@Component("itapUserDAO")
public class ITAPUserDAOImpl implements ITAPUserDAO {

	@Override
	public ITAPUserDtlDO selectUserToEdit(String userId,
			EntityManager managerUser) throws DAOException {
		try {
			ITAPUserDtlDO itapUserDtlDO = (ITAPUserDtlDO) managerUser
					.createQuery(
							"SELECT p FROM ITAPUserDtlDO p where p.userId='"
									+ userId + "'").getSingleResult();
			return itapUserDtlDO;
		} catch (IllegalStateException illegalStateEx) {
			throw new DAOException(
					ITAPConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			throw new DAOException(ITAPConstants.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			throw new DAOException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			throw new DAOException(ITAPConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String saveUserDetails(ITAPUserDtlDO itapUserDtlDO,
			EntityManager managerUser) throws DAOException {
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				itapUserDtlDO = managerUser.merge(itapUserDtlDO);
				managerUser.getTransaction().commit();
			}

			return "SUCCESS";
		} catch (IllegalStateException illegalStateEx) {
			throw new DAOException(
					ITAPConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			throw new DAOException(ITAPConstants.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			throw new DAOException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			throw new DAOException(ITAPConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

	@Override
	public String daleteUser(String id, EntityManager managerUser)
			throws DAOException {
		try {
			managerUser.getTransaction().begin();

			Query q = managerUser
					.createQuery("DELETE FROM  ITAPUserDtlDO p where p.userId ='"
							+ id + "'");
			q.executeUpdate();

			managerUser.getTransaction().commit();
			return id;
		} catch (IllegalStateException illegalStateEx) {
			throw new DAOException(
					ITAPConstants.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION,
					illegalStateEx);
		} catch (IllegalArgumentException illegalArgEx) {
			throw new DAOException(ITAPConstants.INVALID_QUERY_EXCEPTION,
					illegalArgEx);
		} catch (NullPointerException nullPointerEx) {
			throw new DAOException(ITAPConstants.NULL_POINTER_EXCEPTION,
					nullPointerEx);
		} catch (Exception otherEx) {
			throw new DAOException(ITAPConstants.DATABASE_EXCEPTION, otherEx);
		}
	}

}
