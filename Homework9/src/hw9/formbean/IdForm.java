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

public class IdForm extends FormBean {
	private String id;

	public String getId() { return id;    }
	
	public int getFavIdAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be thrown!
		return Integer.parseInt(id);
	}
	
	public void setId(String id) { this.id = id; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (id == null || id.length() == 0) {
			errors.add("Please provide an ID and use delete buttons");
			return errors;
		}

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("Please provide ID using number only");
		}
		
		return errors;
	}
}
