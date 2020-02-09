package com.tesda.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tesda.dao.DbConnectionDAO;
import com.tesda.model.DO.DataConConnectionsDO;

@Component("dbConnectionDAO")
public class DbConnectionDAOImpl implements DbConnectionDAO
{
	private static Logger logger = Logger.getLogger(DbConnectionDAOImpl.class);

	@Override
	public DataConConnectionsDO saveConnection(DataConConnectionsDO dataConConnectionsDO,
			EntityManager managerUser) {
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				dataConConnectionsDO = managerUser.merge(dataConConnectionsDO);
				managerUser.getTransaction().commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataConConnectionsDO;
	}

	@Override
	public List<DataConConnectionsDO> connectionsDashboard(int offSet, int recordsperpage,
			boolean b, String userId, EntityManager managerUser) {
		try {

			/*String query = "SELECT r FROM DataConConnectionsDO r where r.actionBy='" + userId
					+ "' AND r.active='Y' Order By r.actionDt desc";	*/	
			String query = "SELECT r FROM DataConConnectionsDO r where r.active='Y' Order By r.actionDt desc";	
			@SuppressWarnings("unchecked")
			List<DataConConnectionsDO> list = managerUser.createQuery(query).setFirstResult(offSet)
					.setMaxResults(recordsperpage).getResultList();


			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DataConConnectionsDO savedConnection(String dataConConnId, EntityManager managerUser)
			{
		
		try {

			String query = "SELECT r FROM DataConConnectionsDO r where r.dataConConnId="
					+ Long.parseLong(dataConConnId);

			DataConConnectionsDO dataConConnectionsDO = (DataConConnectionsDO) managerUser
					.createQuery(query).getSingleResult();

			return dataConConnectionsDO;
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long connectionsDashboardCount(String userId, EntityManager managerUser)
			{
		try {

			/*String query = "SELECT COUNT(*) FROM DataConConnectionsDO r where r.actionBy='"
					+ userId + '\'';*/
			String query = "SELECT COUNT(*) FROM DataConConnectionsDO r ";
			Long cnt = (Long) managerUser.createQuery(query).getSingleResult();

			
			return cnt;
		}  catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteConnection(String conId, EntityManager managerUser) {
		
		int count = 0;
		boolean sts = false;
		try {
			if (null != managerUser) {
				managerUser.getTransaction().begin();
				Query q1 = managerUser
						.createQuery("DELETE FROM DataConConnectionsDO r where r.dataConConnId =:dataConConnId");
				q1.setParameter("dataConConnId", Long.parseLong(conId));
				count = q1.executeUpdate();
				if (0 != count) {
					sts = true;
				}
				managerUser.getTransaction().commit();
			}
			
			return sts;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
