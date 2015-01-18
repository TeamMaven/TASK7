package model;

import databean.HistoryBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import java.util.Date;

public class HistoryDAO extends GenericDAO<HistoryBean> {
	public HistoryDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(HistoryBean.class, tableName, cp);
	}
	
	// HistoryDAO read method to fetch histiry data based on fund id
	public HistoryBean[] matchFundId(String fundId) throws RollbackException {
		HistoryBean[] history = match(MatchArg.equals("fundId", fundId));
		return history;
	}
	
	// UserDAO read method to fetch employee data based on lastname
	public HistoryBean[] matchPriceDate(Date priceDate) throws RollbackException {
		HistoryBean[] history = match(MatchArg.equals("priceDate", priceDate));
		return history;
	}
	
	// EmployeeDAO create method to insert new employee to employee table
	public void create(HistoryBean history) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(history);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
