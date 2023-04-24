	<jsp:useBean id="calcula" class="beans.BeanCursojsp" type="beans.BeanCursojsp" scope="page"/>	
	
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    	pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJETO JAVA</title>
</head>
<body>
	<h1>TELA INICIAL</h1>
	

	<form action="LoginServlet" method="post">  
		usuario:	
		<input type="text" id="login" name="login">
		<br/>
		Senha:
		<input type="text" id="senha" name="senha">
		<br/>
		<input type="submit" value="login">
		
	</form>
	
	
</body>
</html>