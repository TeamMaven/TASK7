/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import hw9.model.Model;
import hw9.model.UserDAO;
import hw9.model.FavoriteDAO;
import hw9.databean.UserBean;
import hw9.databean.FavoriteBean;

public class UpdateAction extends Action {
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	public UpdateAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "update.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        UserBean activeUser = (UserBean) request.getSession().getAttribute("user");
        
        try {
			request.setAttribute("userlist", userDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}
        
        try {
        	FavoriteBean[] favorite = null;

		    try {
		    	favorite = favoriteDAO.match(MatchArg.equals("favoriteId", Integer.parseInt(request.getParameter("favoriteId"))));
		    } catch (StackOverflowError s) {
		    	errors.add("We are sorry, it seems we cant find the url that you click in our record, please try again");
		    } catch (NumberFormatException n) {
		    	errors.add("We are sorry, it seems we cant find the url that you click in our record, please try again and use number only");
		    }
		    
		    // print any errors and redirect to favorite list
	        try {
	        	if (errors.size() != 0) {
	        		if (activeUser == null){
	        			request.setAttribute("favorite", favoriteDAO.getAllFavorites());
	        		} else {
	        			request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
	        		}
	        		return "favoritelist.jsp";
	        	}         		
	        } catch (RollbackException e) {
	    		errors.add(e.getMessage());
	    	}
		    
		    if (favorite.length == 0) {
		    	errors.add("We are sorry, it seems we cant find the url that you click in our record, please try again");
		    } else {
		    	FavoriteBean bean = new FavoriteBean();
		    	
		    	bean = favoriteDAO.read(Integer.parseInt(sanitize(request.getParameter("favoriteId"))));
		    	//if there is no error update the click count and redirect to the favorite link
		    	favoriteDAO.updateFavorite(bean);
		    	return bean.getUrl();
		    }
		    
		    // if there is error display it
	        try {
	        	if (activeUser==null) {
	        		request.setAttribute("favorite", favoriteDAO.getAllFavorites());
	        	} else {
	        		request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
	        	}
	        } catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "favoritelist.jsp";
	        }		    
		    return "favoritelist.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
		}
    }
}
