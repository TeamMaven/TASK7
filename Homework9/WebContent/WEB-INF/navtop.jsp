<!-- 
 * Ahmad Nazhir
 * anazhir@andrew.cmu.edu
 * 08-600
 * Homework #9
 * December 01, 2014
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title>Homework9</title>
	<style>
		ul {
    		list-style-type: none;
    		margin: 0;
    		padding: 0;
    		overflow: hidden;
		}

		.topnav {
    		float: left;
		}
		
		.leftnav {
    		float: left;
		}
		
		.divflow {
    		float: left;
    		margin-left: 300px;
		}
		
		.diverr {			
			margin-left: auto;
			margin-right: auto; 
			margin-left: 500px;
			font-weight: bold;
			font: large;
		}

		a.toplink:link, a.toplink:visited {
    		display: block;
    		width: 400px;
    		font-weight: bold;
    		font: large;
    		color: #FFFFFF;
    		background-color: #0099ff;
    		text-align: center;
    		padding: 4px;
    		text-decoration: none;
		}

		a.toplink:hover, a.toplink:active {
    		background-color: #9999cc;
		}
		
		a.leftlink:link, a.leftlink:visited {
    		display: block;
    		width: 150px;
    		font-weight: bold;
    		font: large;
    		color: #FFFFFF;
    		background-color: #0099ff;
    		text-align: center;
    		padding: 4px;
    		text-decoration: none;
    		text-transform: uppercase;
		}

		a.leftlink:hover, a.leftlink:active {
    		background-color: #9999cc;
		}
	</style>
</head>
<body>
	<ul>
		<c:forEach var="userList" items="${userlist}">
			<c:if test='${!(empty user)}'>
				<c:if test="${userList.userId ne user.userId}">   			
  					<li class='topnav'><a class="toplink" href="favoritelist.do?uid=${ userList.userId }"><c:out value="${ userList.email }" /></a></li>
  				</c:if>
			</c:if>
  			<c:if test='${(empty user)}'>  			
  				<li class='topnav'><a class="toplink" href="favoritelist.do?uid=${ userList.userId }"><c:out value="${ userList.email }" /></a></li>
  			</c:if>
  		</c:forEach>
	</ul>
	<c:if test='${!(empty message)}'>
		<div class='diverr'>${message}</div>
	</c:if>	
	<jsp:include page="error.jsp" />