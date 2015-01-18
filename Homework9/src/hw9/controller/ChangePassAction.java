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

import hw9.databean.UserBean;
import hw9.formbean.ChangePassForm;
import hw9.model.FavoriteDAO;
import hw9.model.Model;
import hw9.model.UserDAO;

public class ChangePassAction extends Action {
	private static FormBeanFactory<ChangePassForm> formBeanFactory = FormBeanFactory.getInstance(ChangePassForm.class);

	private UserDAO  userDAO;
	private FavoriteDAO favoriteDAO;

	public ChangePassAction(Model model) {
		userDAO  = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "changepass.do"; }
    
    public String perform(HttpServletRequest request) {    	
    	List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        UserBean activeUser = (UserBean) request.getSession().getAttribute("user");
        
        try {
			request.setAttribute("userlist",userDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}
        
        // check if user is login
        try {
        	if (activeUser == null) {
        		errors.add("Please login first");
        		request.setAttribute("favorite", favoriteDAO.getAllFavorites());
        		return "favoritelist.jsp";
        	}
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "favoritelist.jsp";
        } 
    	
		try {
			ChangePassForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
	
			// If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "changepass.jsp";
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "changepass.jsp";
	        }
	    	
	    	UserBean[] user;
	        user = userDAO.read(sanitize(activeUser.getEmail()));
	        
	        if (user[0].getPassword().matches((sanitize(form.getNewPassword())))) {
	        	errors.add("Please dont use old password, provide a new one");
   	        	return "changepass.jsp";
	        }

	    	userDAO.changePass(activeUser.getUserId(),sanitize(activeUser.getEmail()),sanitize(form.getNewPassword()));
			
			request.setAttribute("message","Password changed for user "+activeUser.getEmail());
			return "changepass.jsp";
		} catch (RollbackException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "changepass.jsp";
		} catch (FormBeanException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "changepass.jsp";
		}
    }
}
