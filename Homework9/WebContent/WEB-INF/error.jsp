<!-- 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 01, 2014
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<c:forEach var="error" items="${errors}">
			<div class='diverr' style="color:red"> ${error} </div>
		</c:forEach>
