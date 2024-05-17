package model;

import java.time.LocalDate;

public class Cliente {

    private String cpf;
    private String nome;
    private LocalDate ddn;
    private int idade;
    private String senha;
    private String email;

    public Cliente() {
        this.cpf = "";
        this.nome = "";
        this.ddn = LocalDate.parse("9999-12-31");
        this.idade = 0;
        this.senha = "";
        this.email = "";
    }

    public Cliente(String cpf, String nome, String ddn, int idade, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.ddn = LocalDate.parse(ddn);
        this.idade = idade;
        this.senha = senha;
        this.email = email;
    }

    public String getCpf() {
        return (this.cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return (this.nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDdn() {
        return (this.ddn.toString());
    }

    public void setDdn(String ddn) {
        this.ddn = LocalDate.parse(ddn);
    }

    public int getIdade() {
        return (this.idade);
    }

    public void setIdade(int idade) {
        this.idade = idade;
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

    public String toString() {
        return ("Cliente [CPF = " + cpf + "; nome = " + nome + "; data de nasc. = " + ddn.toString() + "; idade = "
                + idade + "; email = " + email + "; senha = " + senha + ";]");
    }
}
