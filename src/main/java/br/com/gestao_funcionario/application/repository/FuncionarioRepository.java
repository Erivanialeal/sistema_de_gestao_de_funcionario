package br.com.gestao_funcionario.application.repository;

import br.com.gestao_funcionario.application.domain.Funcionario;

import java.util.List;

public interface FuncionarioRepository {
    void salvar(Funcionario funcionario);

    List<Funcionario> findAll();
}
