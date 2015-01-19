package model;

import databean.PositionBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class PositionDAO extends GenericDAO<PositionBean> {
	
	public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PositionBean.class, tableName, cp);
	}
	
	// PositionDAO read method to fetch employee data based on customerId
	public PositionBean[] matchFirstName(int customerId) throws RollbackException {
		PositionBean[] position = match(MatchArg.equals("customerId", customerId));
		return position;
	}
	
	// PositionDAO read method to fetch employee data based on fundId
	public PositionBean[] matchLastName(int fundId) throws RollbackException {
		PositionBean[] position = match(MatchArg.equals("fundId", fundId));
		return position;
	}
	
	// PositionDAO create method to insert new employee to employee table
	public void create(PositionBean position) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(position);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
