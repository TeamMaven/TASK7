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

public class FavoriteForm extends FormBean {
	private String action;
	private String url;
	private String comment;
	private String favId;	
	
	public String getUrl()   	  { return url;     }
	public String getComment()    { return comment; }
	public String getFavoriteId() { return favId;   }
	public String getAction() 	  { return action;  }
	
	public void setUrl(String s)   		{ url    = sanitize(s.trim()); }
	public void setComment(String s)   	{ comment= sanitize(s.trim()); }
	public void setAction(String s) 	{ action = s;        		   }
	
	public int getFavIdAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be thrown!
		return Integer.parseInt(favId);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (action == null || action.length() == 0) {
			errors.add("Action is required");
		}

		if (url == null || url.length() == 0) {
			errors.add("Please provide a favorite URL");
		}
		
		if (comment == null || comment.length() == 0) {
			errors.add("Please provide a comment for this URL");
		}
		
		if (errors.size() > 0) return errors;

        if (url.matches(".*[<>\"].*")) errors.add("URL may not contain angle brackets or quotes");
        
        if (!action.equals("Add Favorite")) errors.add("Invalid action");


		return errors;
	}
	
	private String sanitize(String s) {
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}
