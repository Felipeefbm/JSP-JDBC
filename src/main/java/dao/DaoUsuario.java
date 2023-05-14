package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursojsp;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursojsp usuario) throws Exception {  // criando objetos da classe BeanCursos com o metodo de encapsulamento SET

		try {

			String sql = "insert into usuario(login, senha, nome) values (?, ?, ?)"; // cadastrando novo usuario
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());                       
			insert.setString(2, usuario.getSenha());
			insert.setString(3,usuario.getNome());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	public List<BeanCursojsp> listar() throws Exception {
		List<BeanCursojsp> listar = new ArrayList<BeanCursojsp>();

		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanCursojsp beanCursoJsp = new BeanCursojsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));

			listar.add(beanCursoJsp);
		}
		return listar;
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id  = '" + id + "'";
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public BeanCursojsp consultar(String id) throws Exception {
		String sql = "select * from usuario where id='" + id + "'"; 

		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			BeanCursojsp beanCursoJsp = new BeanCursojsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));

			return beanCursoJsp;
		}

		return null;
	}

	public void atualizar(BeanCursojsp usuario) throws SQLException {
		try {
			String sql = "update usuario set login= ?, senha = ?, nome = ? where id = " + usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();

		}

	}

}
