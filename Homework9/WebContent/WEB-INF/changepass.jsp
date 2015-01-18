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
			<form name="changepassword" action="changepass.do" method="post">
				<table>
					<tr>
				    	<td colspan='3' style="font-size: large; text-align: center;">Change Password For <c:out value="${ user.email }" /></td>
				    </tr>
				    <tr>
				    	<td style="font-size: large;">Password</td>
				    	<td style="font-size: large;">:</td>
				    	<td style="font-size: large;"><input type="password" name="newPassword" size="20" /></td>
				    </tr>
				    <tr>
				    	<td style="font-size: large;">Confirm Pwd</td>
				    	<td style="font-size: large;">:</td>
				    	<td style="font-size: large;"><input type="password" name="confirmPassword" size="20" /></td>
				    </tr>
				    <tr>
				    	<td colspan='3' style="font-size: large; text-align: center;"><input type="submit" value="ChangePass" name="Submit" /></td>
				    </tr>
				</table>
			</form>
		</div>
	</body>
</html>