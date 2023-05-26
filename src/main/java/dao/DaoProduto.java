package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto implements IProdutoDAO {  

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
  
    @Override
	public void create(BeanProduto produto) throws Exception {
		try {
			String sql = "insert into produto (nome, quantidade, valor, categoria_id) values (?, ?, ?, ?)";  
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setString(2, produto.getQuantidade()); 
			insert.setString(3, produto.getValor());
			insert.setLong(4,produto.getCategoria_id());
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

    @Override
	public List <BeanProduto> list() throws Exception {
		
		List <BeanProduto> listar = new ArrayList<BeanProduto>();
		
		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getString("quantidade"));
			beanProduto.setValor(resultSet.getString("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));
			
			listar.add(beanProduto);
		}
		
		return listar;
		
	}
	
    @Override
	public List <BeanCategoria> listaCategorias()throws Exception{
		
		List <BeanCategoria> retorno = new ArrayList<BeanCategoria>();
		
		String sql = "select * from categoria ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			BeanCategoria beancategoria = new BeanCategoria();
			beancategoria.setId(resultSet.getLong("id"));
			beancategoria.setNome(resultSet.getString("nome"));
			retorno.add(beancategoria);
		}
		return retorno;
	}
	
    @Override
	public void update(BeanProduto produto) throws SQLException {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ?, categoria_id = ?  where id = " + produto.getId(); 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getQuantidade());
			preparedStatement.setString(3, produto.getValor());
			preparedStatement.setLong(4,produto.getCategoria_id());

			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}
		
	}
	
	
	
    @Override
	public void delete (String id) {
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
	
    @Override
	public BeanProduto consult (String id) throws Exception {
		String sql = "select * from produto where id='" + id + "'"; 

		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			BeanProduto beanProduto = new BeanProduto();
			beanProduto.setId(resultSet.getLong("id"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getString("quantidade"));
			beanProduto.setValor(resultSet.getString("valor"));
			beanProduto.setCategoria_id(resultSet.getLong("categoria_id"));



			return beanProduto;
		}    

		return null;
	}
	
    @Override
	public boolean validarProduto(String nome) throws Exception {
		String sql = "select count(1) as qtd from produto where nome='" + nome + "'"; 

		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		if (resultSet.next()) {
			
			return resultSet.getInt("qtd") <= 0;  /*Return true*/
		}    

		return false;
	}
	
	
	
}
