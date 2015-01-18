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

public class RegisterForm  extends FormBean{
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String action;
	
    public String getEmail()  	 		{ return email;           }
    public String getFirstName() 		{ return firstName;       }
    public String getLastName()  		{ return lastName;        }
    public String getPassword()  		{ return password;        }
    public String getConfirmPassword()  { return confirmPassword; }
    public String getAction()    		{ return action;          }
    
    public void setEmail(String s)     		 { email 	= sanitize(s.trim());     }
    public void setFirstName(String s)  	 { firstName = sanitize(s.trim());    }
    public void setLastName(String s)  		 { lastName 	= sanitize(s.trim()); }
    public void setPassword(String s)  		 { password 	= sanitize(s.trim()); }
    public void setConfirmPassword(String s) { confirmPassword = s.trim();        }
    public void setAction(String s)    		 { action   	= s;                  }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (email == null || email.length() == 0) errors.add("Please provide an email address");
        if (firstName == null || firstName.length() == 0) errors.add("Please provide a first name");
        if (lastName == null || lastName.length() == 0) errors.add("Please provide a last name");
        if (password == null || password.length() == 0) errors.add("Please provide a password");
        if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Please provide a confirm password");
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;
        
        if (email.matches(".*[<>\"].*")) {errors.add("E-mail Address may not contain angle brackets or quotes");}
        if (firstName.matches(".*[<>\"].*")) {errors.add("First Name may not contain angle brackets or quotes");}
        if (lastName.matches(".*[<>\"].*")) {errors.add("Last Name Address may not contain angle brackets or quotes");}
        if (password.matches(".*[<>\"].*")) {errors.add("Password may not contain angle brackets or quotes");}
        if (confirmPassword.matches(".*[<>\"].*")) {errors.add("Confirm Password may not contain angle brackets or quotes");}
        if (!password.equals(confirmPassword)) {errors.add("Password and Confirm Password do not match");}
        if (!action.equals("Register")) {errors.add("Invalid button");}
		
        return errors;
    }
    
    private String sanitize(String s) {
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}
