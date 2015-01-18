<!-- 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 01, 2014
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<jsp:include page="navtop.jsp" />
		<jsp:include page="navleft.jsp" />
		<div class="divflow">
			<form action="login.do" method="POST">
		    	<table>
		        	<tr>
		            	<td style="font-size: large">Email</td>
		            	<td style="font-size: large">:</td>
		            	<td>
		                	<input type="text" name="email" value="${form.email}" />
		            	</td>
		        	</tr>
		        	<tr>
		            	<td style="font-size: large">Password</td>
		            	<td style="font-size: large">:</td>
		            	<td><input type="password" name="password" /></td>
		        	</tr>
		        	<tr>
		            	<td colspan="3" align="center">
		                	<input type="submit" name="action" value="Login" />
		            	</td>
		        	</tr>
				</table>
			</form>
		</div>
	</body>
</html>