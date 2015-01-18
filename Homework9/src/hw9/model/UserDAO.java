/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import hw9.databean.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(UserBean.class, tableName, cp);
	}
	
	// UserDAO read method to fetch all user data
	public UserBean[] read() throws RollbackException {
		UserBean[] user = match();
		return user;
	}
	
	// UserDAO read method to fetch user data based on an email address
	public UserBean[] read(String email) throws RollbackException {
		UserBean[] user = match(MatchArg.equals("email", email));
		return user;
	}
	
	// UserDAO create method to insert new user to user table
	public void create(UserBean user) throws RollbackException {
		try {
			Transaction.begin();

			// Create a new user in the database with the next user id number using autoincrement
			createAutoIncrement(user);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void changePass(int userId, String email, String newPassword) throws RollbackException {
        try {
        	Transaction.begin();
			UserBean currentUser =read(userId);
			
			if (currentUser == null) {
				throw new RollbackException("Email Address "+email+" no longer exists");
			}
			
			currentUser.setPassword(newPassword);
			update(currentUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
