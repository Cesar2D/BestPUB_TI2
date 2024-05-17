package model;

public class Estabelecimento {
	private String id;
	private String nome;
	private String preco;
	private String tipoComida;
	private String senha;
	private String email;
	private int telefone;
	private String cep;
	private String rua;
	private String tipoEstabelecimento;
	private String codigoVestimenta;
	private String tematica;

	public Estabelecimento() {
		this("", "", "", "", "", "", 0, "", "", "", "", "");
	}

	public Estabelecimento(String id, String nome, String preco, String tipoComida, String senha, String email,
			int telefone, String cep, String rua, String tipoEstabelecimento, String codigoVestimenta,
			String tematica) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tipoComida = tipoComida;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.cep = cep;
		this.rua = rua;
		this.tipoEstabelecimento = tipoEstabelecimento;
		this.codigoVestimenta = codigoVestimenta;
		this.tematica = tematica;
	}

	public String getId() {
		return (this.id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return (this.nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPreco() {
		return (this.preco);
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getTipoComida() {
		return (this.tipoComida);
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public String getEmail() {
		return (this.email);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return (this.senha);
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTelefone() {
		return (this.telefone);
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return (this.cep);
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return (this.rua);
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getTipoEstabelecimento() {
		return (this.tipoEstabelecimento);
	}

	public void setTipoEstabelecimento(String tipoEstabelecimento) {
		this.tipoEstabelecimento = tipoEstabelecimento;
	}

	public String getCodigoVestimenta() {
		return (this.codigoVestimenta);
	}

	public void setCodigoVestimenta(String codigoVestimenta) {
		this.codigoVestimenta = codigoVestimenta;
	}

	public String getTematica() {
		return (this.tematica);
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public String toString() {
		return ("Estabelecimento [id = " + id + "; nome = " + nome + "; preco = " + preco + "; comida = " + tipoComida
				+ "; email = " + email + "; senha = " + senha + "; telefone = " + telefone + "; cep = " + cep
				+ "; rua = " + rua + "; estabel. = " + tipoEstabelecimento + "; cod. vestim. = " + codigoVestimenta
				+ "; tematica = " + tematica + ";]");
	}
}
