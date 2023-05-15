<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>


	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>
	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" classe="field-long"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}"></td>
					</tr>

					<tr>
						<td>Fone:</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}"></td>
					</tr>


					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<div class="container align-columns">
			<table class="responsive-table">
				<caption>Usuários cadastrados</caption>
				<tr>
					<th>Id</th>
					<th>Login</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>Delete</th>
					<th>Editar</th>
					

				</tr>
				<c:forEach items="${usuarios}" var="user">
					<!-- recebe a injeção de dados pela classe Usuario  -->
					<tr>
						<td class="align-column"><c:out value="${user.id}"></c:out></td>
						<td class="align-column"><c:out value="${user.login}"></c:out></td>
						<td class="align-column"><c:out value="${user.nome}"></c:out></td>
						<td class="align-column"><c:out value="${user.fone}"></c:out></td>



						<td class="button-cell"><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.jpg" alt="excluir" title="Excluir" width="50px" height="50px"> </a></td>

						<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img alt="Editar" title="Editar" src="resources/img/editar.jpg"  width="50px" height="50px"> </a></td>


					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>