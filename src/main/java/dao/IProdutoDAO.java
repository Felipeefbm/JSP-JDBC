package dao;

import java.util.List;

import beans.BeanCategoria;
import beans.BeanProduto;

public interface IProdutoDAO {  
	
	    void create(BeanProduto produto) throws Exception;
	    List<BeanProduto> list() throws Exception;
	    List<BeanCategoria> listaCategorias() throws Exception;
	    void update(BeanProduto produto) throws Exception;
	    void delete(String id) throws Exception;
	    BeanProduto consult(String id) throws Exception;
	    boolean validarProduto(String nome) throws Exception;
}



