package br.com.gestao_funcionario.application.api;

import br.com.gestao_funcionario.application.domain.Funcionario;

import java.util.List;
import java.util.UUID;

public interface FuncionarioAPI {
    void cadastrarFuncionarios(Funcionario funcionario);
    List<Funcionario> buscarFuncionarios();
    void atualizarFuncionarios(UUID idFuncioanrio);
    void excluir(UUID idFuncionarios);
}
