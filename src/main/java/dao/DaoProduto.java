package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
   // recebe um objeto BeanProduto como argumento e insere os dados desse objeto em uma tabela chamada produto no banco de dados
	public void create(BeanProduto produto) throws Exception {
		try {
			String sql = "insert into produto (nome, quantidade, valor) values (?, ?, ?)";  
			PreparedStatement insert = connection.prepareStatement(sql); // O objeto PreparedStatement permite definir os valores para os placeholders na instrução SQL e executar a instrução.
			insert.setString(1, produto.getNome());
			insert.setInt(2, produto.getQuantidade());  // valores especificados pelo metodo get
			insert.setDouble(3, produto.getValor());
			insert.execute();                      // Isso insere uma nova linha na tabela produto com os valores especificados

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	
	public List <BeanProduto> read() throws Exception {
		
		List <BeanProduto> listar = new ArrayList<BeanProduto>();
		
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getInt("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			
			listar.add(beanProduto);
		}
		
		return listar;
		
	}
	
	public void update(BeanProduto produto) throws SQLException {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor ? where id = " + produto.getId(); 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setInt(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		
	}
	
	
	
	
	public void delete (Long id) {
		try {
			String sql = "delete from produto where id  = '" + id + "'";
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
	
}
