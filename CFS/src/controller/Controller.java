package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;

import controller.Action;
import databean.EmployeeBean;
//import hw9.databean.FavoriteBean;
import model.Model;
import model.EmployeeDAO;
//import hw9.model.UserDAO;


public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	private EmployeeDAO employeeDAO;
//	private FavoriteDAO favoriteDAO;

	public void init() throws ServletException {
        Model model = new Model(getServletConfig());

//        Action.add(new CreateEmp(model));
//        Action.add(new RegisterAction(model));
//        Action.add(new ChangePassAction(model));
//        Action.add(new FavoriteListAction(model));
//        Action.add(new AddAction(model));
//        Action.add(new DeleteAction(model));  
//        Action.add(new UpdateAction(model));
//        Action.add(new LogoutAction(model));        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    private String performTheAction(HttpServletRequest request) {
        String      servletPath = request.getServletPath();
        String      action		= getActionName(servletPath);

        if (action.equals("Homework9")) {
        	// This is the default action
			return Action.perform("favoritelist.do",request);
        }
        
        if (action.equals("welcome")) {
        	// User is logged in, but at the root of our web app
			return Action.perform("favoritelist.do",request);
        }
       
        return Action.perform(action,request);
    }
    
    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}
    	
    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	response.sendRedirect("http://" + nextPage);
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}