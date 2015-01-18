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

import hw9.databean.FavoriteBean;
import hw9.databean.UserBean;
import hw9.formbean.FavoriteForm;
import hw9.model.FavoriteDAO;
import hw9.model.Model;
import hw9.model.UserDAO;

public class AddAction extends Action {
	private FormBeanFactory<FavoriteForm>  favoriteFormFactory  = FormBeanFactory.getInstance(FavoriteForm.class);
	
	private UserDAO userDAO;
	private FavoriteDAO favoriteDAO;

	public AddAction(Model model) {
		userDAO = model.getUserDAO();
		favoriteDAO = model.getFavoriteDAO();
	}

	public String getName() { return "add.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        UserBean activeUser = (UserBean) request.getSession().getAttribute("user");
        
        try {
			request.setAttribute("userlist", userDAO.read());
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
        	// Fetch the items now, so that in case there is no form or there are errors
       		// We can just dispatch to the JSP to show the favorite list (and any errors)
       		request.setAttribute("favorite", favoriteDAO.getUserFavorites(activeUser.getUserId()));
       		
	        FavoriteForm form = favoriteFormFactory.create(request);
        	request.setAttribute("form", form);

	        errors.addAll(form.getValidationErrors());
	        if (errors.size() > 0) {
	        	return "favoritelist.jsp";
	        }
	        
	        FavoriteBean bean = new FavoriteBean();
       		bean.setUserId(activeUser.getUserId());
       		bean.setUrl(super.sanitize(form.getUrl()));
       		bean.setComment(super.sanitize(form.getComment()));
       		bean.setClickCount(0);
       		
        	favoriteDAO.create(bean);
        	
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
