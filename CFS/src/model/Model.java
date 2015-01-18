package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;	

	public Model(ServletConfig config) throws ServletException {		
		// initialise tables in my-sql database
		try {			
			String jdbcDrive 	= config.getInitParameter("jdbcDriverName");
			String jdbcURL      = config.getInitParameter("jdbcURL");
			ConnectionPool cp 	= new ConnectionPool(jdbcDrive, jdbcURL);
			
			String employeeTbl = "employee";
			String customerTbl = "customer";
			
			employeeDAO = new EmployeeDAO(cp, employeeTbl);
			customerDAO = new CustomerDAO(cp, customerTbl);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public EmployeeDAO getEmployeeDAO()  { return employeeDAO; }
	public CustomerDAO getCustomerDAO()  { return customerDAO; }	
}