package beans;

public class BeanProduto {
	
	private Long id;
	private String nome;
	private String Quantidade; 
	private String valor;
	private Long categoria_id;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantidade() {
		return Quantidade;
	}
	public void setQuantidade(String quantidade) {
		Quantidade = quantidade;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Long getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	
	

}
