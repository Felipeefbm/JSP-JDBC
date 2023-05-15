
<jsp:useBean id="calcula" class="beans.BeanCursojsp"
	type="beans.BeanCursojsp" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJETO JAVA</title>

<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>

	<div class="login">
		<h1>Login</h1>					 <!-- post vai "cair" no metodo doPost de LoginServlet-->
		<form action="LoginServlet" method="post">   <!-- LoginServlet é a classe que está gerenciando os dados recebidos pelo index -->
			
			<input type="text" name="login" placeholder="Username"
				required="required" /> <input type="password" name="senha"
				placeholder="Password" required="required" />
				
			<button type="submit" value="login"
				class="btn btn-primary btn-block btn-large">Let me in.</button>
		</form>
	</div>


</body>
</html>