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

public class ChangePassForm extends FormBean {	
	private String newPassword;
	private String confirmPassword;
	
	public String getNewPassword()     { return newPassword;     }
	public String getConfirmPassword() { return confirmPassword; }

	public void setNewPassword(String s)     { newPassword     = s.trim(); }
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newPassword == null || newPassword.length() == 0) {errors.add("Please provide a New Password");}
		if (confirmPassword == null || confirmPassword.length() == 0) {errors.add("Please provide a Confirm Password");}
		
		if (errors.size() > 0) return errors;
		
		if (newPassword.matches(".*[<>\"].*")) {errors.add("Password may not contain angle brackets or quotes");}
        if (confirmPassword.matches(".*[<>\"].*")) {errors.add("Confirm Password may not contain angle brackets or quotes");}
		if (!newPassword.equals(confirmPassword)) {errors.add("New Password and Confirm Pwd do not match");}

		return errors;
	}
}
