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

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import hw9.formbean.IdForm;
import hw9.model.Model;
import hw9.model.UserDAO;
import hw9.model.FavoriteDAO;
import hw9.databean.UserBean;
import hw9.databean.FavoriteBean;

public class DeleteAction extends Action {
	private FormBeanFactory<IdForm> favFormFactory = FormBeanFactory.getInstance(IdForm.class);
	
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	public DeleteAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "delete.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        UserBean activeUser = (UserBean) request.getSession().getAttribute("user");
        // create a user list for top nav
        try {
			request.setAttribute("userlist", userDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}
        // check if user is login
        try {
        	if (activeUser == null){
        		errors.add("Please login first");
        		request.setAttribute("favorite", favoriteDAO.getAllFavorites());
        		return "favoritelist.jsp";
        	}
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
        } 
        // user is login and continue to process requests
        try {
	        IdForm form = favFormFactory.create(request);
	        errors.addAll(form.getValidationErrors());
	        
	        if (errors.size() > 0) {
	        	request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
	        	return "favoritelist.jsp";
	        }
	        
	        try {
	        	FavoriteBean bean = new FavoriteBean();
	        	bean = favoriteDAO.read(form.getFavIdAsInt());
	        	if (bean.getUserId() != activeUser.getUserId()){
	        		errors.add("We are sorry, it seems we cant delete the favorite link, please use delete button");
	        	}
	    	} catch (NullPointerException n) {
	    		errors.add("We are sorry, it seems we cant delete the favorite link, please use delete button");
	    	}
	        
	        if (errors.size() > 0) {
	        	request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
	        	return "favoritelist.jsp";
	        }

	    	favoriteDAO.delete(form.getFavIdAsInt());
	    	
	    	return "favoritelist.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
        }
    }
}
