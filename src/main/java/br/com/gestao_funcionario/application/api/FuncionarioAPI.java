package br.com.gestao_funcionario.application.api;

import br.com.gestao_funcionario.application.domain.Funcionario;

import java.util.List;
import java.util.UUID;

public interface FuncionarioAPI {
    void cadastrarFuncionarios(Funcionario funcionario);
    List<Funcionario> buscarFuncionarios();
    Funcionario atualizarFuncionarios(UUID idFuncionario, Funcionario funcionarioAtualizado);
    void excluirTodosOsFuncionarios();
    void excluirFuncionarioPorId(UUID idFuncionario);
}
