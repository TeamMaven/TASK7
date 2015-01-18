/* 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 1, 2014
*/

package hw9.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hw9.model.Model;
/*
 * Logs out by setting the "user" session attribute to null.
 */
public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);

        return "favoritelist.do";
    }
}
