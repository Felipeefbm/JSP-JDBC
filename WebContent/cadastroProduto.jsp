<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cadastro de produto</title>
<link rel="stylesheet" href="resources/css/cadastroProduto.css">
</head>
<body>
<a href="acessoliberado.jsp">Inicío</a>
<a href="index.jsp">Sair</a>


	<center>
		<h1>Cadastro de produto</h1>
		<h3 style="color: red;">${msg}</h3>
	</center>
	<form action="salvarProduto" method="post" id="formProduct">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${product.id}"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${product.nome}"></td>
					</tr>

					<tr>
						<td>Quantidade:</td>
						<td><input type="text" id="quantidade"
							name="quantidade" value="${product.quantidade}"></td>
					</tr>

					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor"
							value="${product.valor}"></td>
					</tr>
					
						<tr>
						<td>Categoria:</td>
						<td>
							<select id="categorias" name="categoria_id">
								<c:forEach items="${categorias}" var="cat">
									<option value="${cat.id}" id="${cat.id}"
									<c:if test="${cat.id == product.categoria_id}">
										<c:out value = "selected=selected"/>
									</c:if>>
									  ${cat.nome}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formProduct').action = 'salvarProduto?acao=reset'"></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<div class="container align-columns">
			<table class="responsive-table">
				<caption>Produtos cadastrados</caption>
				<tr>
					
					<th>Id</th>
					<th>Categoria_id</th>
					<th>Nome</th>
					<th>Quantidade</th>
					<th>valor</th>	
					<th>Delete</th>
					<th>Editar</th>
					

				</tr>
				<c:forEach items="${produtos}" var="product">
					<tr>
						<td class="align-column"><c:out value="${product.id}"></c:out></td>
						<td class="align-column"><c:out value="${product.categoria_id}"></c:out></td>
						<td class="align-column"><c:out value="${product.nome}"></c:out></td>
						<td class="align-column"><c:out value="${product.quantidade}"></c:out></td>
						<td class="align-column"><c:out value="${product.valor}"></c:out></td>



						<td class="button-cell"><a
							href="salvarProduto?acao=delete&product=${product.id}"><img
								src="resources/img/excluir.jpg" alt="excluir" title="Excluir"
								width="50px" height="50px"> </a></td>

						<td><a href="salvarProduto?acao=editar&product=${product.id}"><img
								alt="Editar" title="Editar" src="resources/img/editar.jpg"
								width="50px" height="50px"> </a></td>
								
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>