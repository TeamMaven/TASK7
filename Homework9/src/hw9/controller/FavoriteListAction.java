/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.controller;

import hw9.model.Model;
import hw9.model.UserDAO;
import hw9.model.FavoriteDAO;
import hw9.databean.UserBean;
import hw9.databean.FavoriteBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

public class FavoriteListAction extends Action {
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	public FavoriteListAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "favoritelist.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        UserBean activeUser = (UserBean) request.getSession().getAttribute("user");
        
        try {
			request.setAttribute("userlist", userDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}        
        
        // check if uid parameter exists
        if (request.getParameter("uid") != null){
        	int userId=0;
        	try {
        		userId = Integer.parseInt(request.getParameter("uid"));
        		FavoriteBean[] favorite = favoriteDAO.getUserFavorites(userId);
        		if (favorite.length != 0) {
        			request.setAttribute("favorite", favorite);
        		} else {
        			errors.add("We are sorry, this user doesn't have a favorite link list");
        		}
        	} catch (NumberFormatException n) {
        		errors.add("We are sorry, it seems we cant find favorite for this user in our record, please try again and use number only");
        	} catch (RollbackException e) {
        		errors.add(e.getMessage());
        	}
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
        // if there is no error process the request
        try {
        	if (request.getParameter("uid") != null){
        		//continue to favorite list
        	} else if (activeUser==null) {
        		request.setAttribute("favorite", favoriteDAO.getAllFavorites());
        	} else {
        		request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
        	}
        	return "favoritelist.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
        }
    }
}