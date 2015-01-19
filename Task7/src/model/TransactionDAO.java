package model;

import databean.TransactionBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	
	public TransactionDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(TransactionBean.class, tableName, cp);
	}
	
	// TransactionDAO read method to fetch data based on transactionId
	public TransactionBean[] matchLastName(int transactionId) throws RollbackException {
		TransactionBean[] transaction = match(MatchArg.equals("transactionId", transactionId));
		return transaction;
	}
	
	// TransactionDAO create method to insert new transaction
	public void create(TransactionBean transaction) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(transaction);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}

}
