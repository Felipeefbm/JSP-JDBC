package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String product = request.getParameter("product");
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(product);
				request.setAttribute("produtos", daoProduto.list());
				
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beanProduto = daoProduto.consult(product);
				request.setAttribute("product", beanProduto);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("produtos", daoProduto.list()); 
			}
			
			request.setAttribute("categorias", daoProduto.listaCategorias());
			view.forward(request, response);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.list());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id"); 
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoria_id");


			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : 0); 
			produto.setNome(nome);
			produto.setQuantidade(quantidade);
			produto.setValor(valor);
			produto.setCategoria_id(Long.parseLong(categoria));

			try {

				if (id == null || id.isEmpty() && !daoProduto.validarProduto(nome)) {
					request.setAttribute("msg", "produto já foi inserido!");
				}

				if (id == null || id.isEmpty() && daoProduto.validarProduto(nome)) { 
					daoProduto.create(produto);

				} else if (id != null && !id.isEmpty()) {
					daoProduto.update(produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.list());
				request.setAttribute("categorias", daoProduto.listaCategorias());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
