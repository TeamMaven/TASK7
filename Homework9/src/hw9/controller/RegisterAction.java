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
import hw9.formbean.RegisterForm;
import hw9.model.Model;
import hw9.model.UserDAO;

public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
            	
    	// If user is already logged in, redirect to todolist.do
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
	    	RegisterForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	return "register.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	return "register.jsp";
	        }
	        
	        UserBean[] user;
	        user = userDAO.read(form.getEmail());
	        
	        if (user.length != 0) {
	        	errors.add("A user with this email already exists");
   	        	return "register.jsp";
	        }

       		if (form.getAction().equals("Register")) {
       			UserBean newUser = new UserBean();
       			newUser.setEmail(sanitize(form.getEmail()));
       			newUser.setFirstName(sanitize(form.getFirstName()));
       			newUser.setLastName(sanitize(form.getLastName()));
       			newUser.setPassword(sanitize(form.getPassword()));
	       		userDAO.create(newUser);	       			
	       		session.setAttribute("user", newUser);
	       		return("favoritelist.do");
       		}
	     
	        // If redirectTo is null, redirect to the "favoritelist" action
			return "favoritelist.do";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "register.jsp";
        }
    }
}
