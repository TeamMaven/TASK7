/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm  extends FormBean{
    private String email;
    private String password;
    private String action;
	
    public String getEmail()  	{ return email;    }
    public String getPassword() { return password; }
    public String getAction() 	{ return action;   }
    
    public void setEmail(String s)     { email 	  = sanitize(s.trim()); }
    public void setPassword(String s)  { password = sanitize(s.trim()); }
    public void setAction(String s)    { action   = s;                  }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (email == null || email.length() == 0) {errors.add("Please provide an email address");}
        if (password == null || password.length() == 0) {errors.add("Please provide a password");}
        if (action == null) {errors.add("Button is required");}

        if (errors.size() > 0) return errors;

        if (!action.equals("Login")) {errors.add("Invalid button");}
        if (email.matches(".*[<>\"].*")) {errors.add("E-mail Address may not contain angle brackets or quotes");}
		
        return errors;
    }
    
    private String sanitize(String s) {
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}
