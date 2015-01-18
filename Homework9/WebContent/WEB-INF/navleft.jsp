<!-- 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 01, 2014
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<div class='leftnav'>
			<ul>
				<li><a class='leftlink' href=></a></li>
				<li><a class='leftlink' href=></a></li>
				<li><a class='leftlink' href=></a></li>
				<li><a class='leftlink' href=></a></li>
		  		<c:if test='${(empty user)}'>
			  		<li><a class='leftlink' href="favoritelist.do">Home</a></li>
			  		<li><a class='leftlink' href=></a></li>
			  		<li><a class='leftlink' href="login.do">Login</a></li>
			  		<li><a class='leftlink' href=></a></li>
			  		<li><a class='leftlink' href="register.do">Register</a></li>
		  		</c:if>
		  		<c:if test='${(!empty user)}'>
			 	 	<li><a class='leftlink' href="favoritelist.do">Manage Your Favorites</a></li>
			 	 	<li><a class='leftlink' href=></a></li>
			 	 	<li><a class='leftlink' href="changepass.do">Change Password</a></li>
			 	 	<li><a class='leftlink' href=></a></li>
			 	 	<li><a class='leftlink' href="logout.do">Logout</a></li>
		 	 	</c:if>
		 	 	<li><a class='leftlink' href=></a></li>
		 	 	<li><a class='leftlink' href=></a></li>
		 	 	<li><a class='leftlink' href=></a></li>
		 	 	<li><a class='leftlink' href=></a></li>
		 	 	<li><a class='leftlink' href=></a></li>
			</ul>
		</div>