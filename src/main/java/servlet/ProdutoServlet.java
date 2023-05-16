package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursojsp;
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
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("produtos", daoProduto.read());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("update")) {
				BeanCursojsp beanCursoJsp = daoProduto.consultar(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) { // quando a requisição for feita pela tela de cadastro de
																// usuario. Liste todos os usuarios
				RequestDispatcher view = request // redireciona a pagina para a tela de cadastroUsuariojsp
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar()); // vai injetar toda a lista de usuarios que está
																		// dentro do banco de dados
				view.forward(request, response); // na tabela usuarios do cadastroUsuario.jsp
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id"); // dados que estao vindo da tela pelo botao "salvar"
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String fone = request.getParameter("fone");

			BeanCursojsp usuario = new BeanCursojsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0); // criando um novo usuario com uma instância da
																	// classe Bean e salvando os dados recebidos pelo
																	// submit do front
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setFone(fone);

			try {
				
				if(id == null || id.isEmpty()&& !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Usuário já existe com o mesmo login!");
				}

				if (id == null || id.isEmpty()                   // se id existir atualiza, se nao, salva novo usuario
						&& daoUsuario.validarLogin(login)) {        // valida e depois salva
					
					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty()) {
					daoUsuario.atualizar(usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
	
	
	
	

}
