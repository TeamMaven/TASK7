package model;

import databean.FundBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class FundDAO extends GenericDAO<FundBean> {
	
	public FundDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FundBean.class, tableName, cp);
	}
	
	// UserDAO read method to fetch employee data based on fundId
	public FundBean[] matchLastName(int fundId) throws RollbackException {
		FundBean[] fund = match(MatchArg.equals("fundId", fundId));
		return fund;
	}
	
	// FundDAO create method to insert new employee to employee table
	public void create(FundBean fund) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(fund);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
}