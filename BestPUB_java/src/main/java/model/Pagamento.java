package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pagamento {
	private String id;
	private String nomeTitular;
	private String formaPagamento;
	private int cvv;
	private String numeroCartao;
	private LocalDate validade;
	private LocalDateTime dataHora;

	public Pagamento() {
		this("", "", "", 000, "", "2050-12-12", "2007-12-03T10:15:30");
	}

	public Pagamento(String id, String nomeTitular, String formaPagamento, int cvv, String numeroCartao,
			String validade, String dataHora) {
		this.id = id;
		this.nomeTitular = nomeTitular;
		this.formaPagamento = formaPagamento;
		this.cvv = cvv;
		this.numeroCartao = numeroCartao;
		this.validade = LocalDate.parse(validade);
		this.dataHora = LocalDateTime.parse(dataHora);
	}

	public String getId() {
		return (this.id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeTitular() {
		return (this.nomeTitular);
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public String getFormaPagamento() {
		return (this.formaPagamento);
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getCvv() {
		return (this.cvv);
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getNumeroCartao() {
		return (this.numeroCartao);
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getValidade() {
		return (this.validade.toString());
	}

	public void setValidade(String validade) {
		this.validade = LocalDate.parse(validade);
	}

	public String getDataHora() {
		return (this.dataHora.toString());
	}

	public void setDataHora(String dataHora) {
		this.dataHora = LocalDateTime.parse(dataHora);
	}

	public void setDataHoraNow() {
		this.dataHora = LocalDateTime.now();
	}

	public String toString() {
		return ("RegistroPag [id = " + id + "; nome tit. = " + nomeTitular + "; forma de pag. = " + formaPagamento
				+ "; cvv = " + cvv + "; num. cartao = " + numeroCartao + "; validade = " + validade.toString()
				+ "; data/hora = " + dataHora + ";]");
	}
}
