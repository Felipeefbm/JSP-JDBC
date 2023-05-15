package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursojsp;
import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin(); // instância de DaoLogin

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BeanCursojsp beanCursoJsp = new BeanCursojsp();

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			if (daoLogin.validarLogin(login, senha)) { // recebe o login e senha do front e manda requisição pro backend
														// fazer a pesquisa
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
