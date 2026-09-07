package br.com.gestao_funcionario.application.domain;

import java.util.UUID;

public class Funcionario {
    private UUID idFuncionarios;
    private String nome;
    private String designacao;
    private String salario;
    private String telefone;
    private String endereco;

    public Funcionario() {
    }

    public Funcionario(UUID idFuncionarios, String nome, String designacao, String salario, String telefone, String endereco) {

        this.idFuncionarios = idFuncionarios;
        this.nome = nome;
        this.designacao = designacao;
        this.salario = salario;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public UUID getIdFuncionarios() {
        return idFuncionarios;
    }

    public String getNome() {
        return nome;
    }

    public String getDesignacao() {
        return designacao;
    }

    public String getSalario() {
        return salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereço() {
        return endereco;
    }

    public void put(UUID idFuncionarios, Funcionario funcionario) {
    }
}
