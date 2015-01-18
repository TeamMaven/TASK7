package model;

import databean.CustomerBean;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CustomerBean.class, tableName, cp);
	}
	
	// CustomerDAO read method to fetch employee data based on firstname
	public CustomerBean[] matchFirstName(String firstName) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("firstName", firstName));
		return customer;
	}
	
	// UserDAO read method to fetch employee data based on lastname
	public CustomerBean[] matchLastName(String lastName) throws RollbackException {
		CustomerBean[] customer = match(MatchArg.equals("lastName", lastName));
		return customer;
	}
	
	// EmployeeDAO create method to insert new employee to employee table
	public void create(CustomerBean customer) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new employee in the database
			create(customer);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void changePass(String userName, String newPassword) throws RollbackException {
        try {
        	Transaction.begin();
			CustomerBean currentCustomer = read(userName);
			
			if (currentCustomer == null) {
				throw new RollbackException("User Name "+userName+" no longer exists");
			}
			
			currentCustomer.setPassword(newPassword);
			update(currentCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
