/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private UserDAO 	userDAO;
	private FavoriteDAO favoriteDAO;	

	public Model(ServletConfig config) throws ServletException {		
		// initialise tables in my-sql database
		try {			
			String jdbcDrive 	= config.getInitParameter("jdbcDriverName");
			String jdbcURL      = config.getInitParameter("jdbcURL");
			String andrewId 	= "anazhir_";
			ConnectionPool cp 	= new ConnectionPool(jdbcDrive, jdbcURL);
			
			String userTbl = andrewId + "user";
			String listTbl = andrewId + "favorite";
			
			userDAO = new UserDAO(cp, userTbl);
			favoriteDAO = new FavoriteDAO(cp, listTbl);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public UserDAO 	   getUserDAO()      { return userDAO; }
	public FavoriteDAO getFavoriteDAO()  { return favoriteDAO; }	
}
