package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {
	
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha)throws Exception{
		
		String sql = "select * from usuario where login =  '" + login + "' and senha = '" + senha + "' ";  //pesquisa
		PreparedStatement statement = connection.prepareStatement(sql);  // instrução pro banco
		ResultSet resultSet = statement.executeQuery();	  // objeto da pesquisa
		if (resultSet.next()) {           // existe usuario?
			return true;  // possui usuario
		}else {
			return false;  // usuario nao existe no banco
		}
	}

}
