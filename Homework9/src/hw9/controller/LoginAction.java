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
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import hw9.databean.UserBean;
import hw9.formbean.LoginForm;
import hw9.model.Model;
import hw9.model.UserDAO;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
            	
    	// If user is already logged in, redirect to favorite list
        if (session.getAttribute("user") != null) {
        	return "favoritelist.do";
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			request.setAttribute("userlist",userDAO.read());
		} catch (RollbackException e1) {
			errors.add("No User Data");
		}
        
        try {
	    	LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        UserBean[] user = userDAO.read(sanitize(form.getEmail()));
	        
	        if (user.length == 0) {
	            errors.add("User Name not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!user[0].getPassword().equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        session.setAttribute("user",user[0]);
	        
	        // If redirectTo is null, redirect to the "favoritelist" action
			return "favoritelist.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "login.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "login.jsp";
        }
    }
}
