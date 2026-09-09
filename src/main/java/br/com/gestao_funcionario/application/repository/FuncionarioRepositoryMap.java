package br.com.gestao_funcionario.application.repository;

import br.com.gestao_funcionario.application.domain.Funcionario;

import java.util.*;

public class FuncionarioRepositoryMap implements FuncionarioRepository {
    private  final Map<UUID, Funcionario> funcionarios = new HashMap<>();

    @Override
    public void salvar(Funcionario funcionario) {
        System.out.println("[inicia] FuncionarioRepositoryMap - salvar");
        funcionarios.put(
                funcionario.getIdFuncionarios(),
                funcionario
        );
        System.out.println("Quantidade no Map: " + funcionarios.size());
        System.out.println("[Finaliza] FuncionarioRepositoryMap - salvar");

    }

    @Override
    public List<Funcionario> findAll() {
        System.out.println("[inicia] FuncionarioRepositoryMap - findAll");
        System.out.println("Quantidade no Map: " + funcionarios.size());
        List<Funcionario> lista = new ArrayList<>(funcionarios.values());
        System.out.println("Quantidade na lista: " + lista.size());
        System.out.println("[Finaliza] FuncionarioRepositoryMap - findAll");
        return lista;
    }

    @Override
    public Funcionario findById(UUID idFuncioanrio) {
        System.out.println("[inicia] FuncionarioRepositoryMap - findById");
        Funcionario funcionario = funcionarios.get(idFuncioanrio);
        System.out.println("[finaliza] FuncionarioRepositoryMap - findById");
        return funcionario;
    }
}
