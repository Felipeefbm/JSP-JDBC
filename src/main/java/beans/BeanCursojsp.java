package beans;

public class BeanCursojsp { // classe para trafegar os dados na rede (front, backend) recebe os dados da
							// tela de cadastro pela servelet Usuario
							// e criando um novo usuario/objeto e salvando no banco de dados

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String fone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

}
