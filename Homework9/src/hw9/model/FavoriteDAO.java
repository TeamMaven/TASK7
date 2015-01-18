/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.model;

import hw9.databean.FavoriteBean;

import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;


public class FavoriteDAO extends GenericDAO<FavoriteBean> {
	
	public FavoriteDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(FavoriteBean.class, tableName, cp);
	}
	
	// a method for fetching favorite list based on a user id
	public FavoriteBean[] getUserFavorites(Integer userId) throws RollbackException {
	    
		// Calls GenericDAO's match() method.		
	 	FavoriteBean[] favorite = match(MatchArg.equals("userId", userId));
	 		
	 	// Sort the list in favorite id order
	    java.util.Arrays.sort(favorite, new Comparator<FavoriteBean>() {
	    	public int compare(FavoriteBean item1, FavoriteBean item2) {
	    		return item1.getFavoriteId() - item2.getFavoriteId();
	         }
	 	});
	         
	    return favorite;
	}
	
	public FavoriteBean[] getAllFavorites() throws RollbackException {
	    
		// Calls GenericDAO's match() method.		
	 	FavoriteBean[] favorite = match();
	 		
	    // Sort the list in favorite id order
	    java.util.Arrays.sort(favorite, new Comparator<FavoriteBean>() {
	    	public int compare(FavoriteBean item1, FavoriteBean item2) {
	    		return item1.getFavoriteId() - item2.getFavoriteId();
	        }
	 	});
	         
	    return favorite;
	}
	
	// a method to insert new favorite url of an user id into favorite table
	public void create(FavoriteBean favorite) throws RollbackException {
		try {
			Transaction.begin();
			
			// Create a new favorite in the database with the next favoriteId number
			createAutoIncrement(favorite);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	// a method to update new favorite url of an user id into favorite table based on favorite id
	public void updateFavorite(FavoriteBean favorite) throws RollbackException {
		try {
			Transaction.begin();
			
			FavoriteBean[] oldFavorite = match(MatchArg.equals("favoriteId", favorite.getFavoriteId()));
			
			int newCount=0;
			
			if (oldFavorite.length != 0){	
				newCount = oldFavorite[0].getClickCount() + 1;
			}
			
			favorite.setClickCount(newCount);			

			update(favorite);

			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
