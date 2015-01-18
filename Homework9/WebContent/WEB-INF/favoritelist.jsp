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
			<c:if test='${!(empty user)}'>
		        <form action="add.do" method="POST">
		   			<table>
		        		<tr><td colspan="3"><hr/></td></tr>
						<tr>
							<td style="font-size: large">URL</td>	
							<td style="font-size: large">:</td>	
							<td><input type="text" size="40" name="url" value="${form.url}" /></td>
						</tr>	
						<tr>	
							<td style="font-size: large">Comment</td>	
							<td style="font-size: large">:</td>	
							<td><input type="text" size="40" name="comment" value="${form.comment}"/></td>	
						</tr>	
						<tr>	
							<td colspan="3" style="text-align: center;">	
								<input type="submit" name="action" value="Add Favorite"/>
							</td>	
						</tr>	
						<tr><td colspan="3"><hr/></td></tr>	
					</table>	
		        </form>
			</c:if>
			<table>
				<c:forEach var="favorites" items="${favorite}">
	           		<tr>
	       				<c:if test="${favorites.userId eq user.userId}"> 
		       				<td>
					            <form action="delete.do" method="POST">
		                			<input type="hidden" name="id" value="${ favorites.favoriteId }" />
		                			<input style="font-size: large" type="submit" name="button" value="delete" />
		           				</form>
		        			</td>
	        			</c:if>
	        			<td valign="baseline">
	        				<span style="font-size: large">
	        					Favorit Link :
	        					<a href="update.do?favoriteId=${ favorites.favoriteId }"><c:out value="${ favorites.url }" /></a>
	        				</span><br/>
	        				<span style="font-size: large">
	        					User Comment :
	        					<c:out value="${ favorites.comment }" />
	        				</span><br/>
	        				<span style="font-size: large">
	        					View : 
	        					<c:out value="${ favorites.clickCount }" />
	        					&nbsp;times
	        				</span>
	        			</td>
	   				</tr>
	   			</c:forEach>
			</table>
		</div>
    </body>
</html>