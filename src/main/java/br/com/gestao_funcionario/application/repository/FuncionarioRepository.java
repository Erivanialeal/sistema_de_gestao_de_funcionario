package br.com.gestao_funcionario.application.repository;

import br.com.gestao_funcionario.application.domain.Funcionario;

import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository {
    void salvar(Funcionario funcionario);

    List<Funcionario> findAll();
    Funcionario findById(UUID idFuncioanrio);
    void excluirTodosOsFuncionarios();
    void excluirFuncionarioPorId(UUID idFuncionario);
}
