<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<body>
   ${hello}
   
   <form method="POST" action="/home">
		<textarea rows="10" cols="50" name="data">${data}</textarea>
		<input type="submit" />
	</form>
	${result}	
	<c:forEach items="${errors}" var="error">
		<li>
		<c:out value="${error}" />	
		</li>
	</c:forEach>
</body>
</html>