package br.com.gestao_funcionario.application.service;

import br.com.gestao_funcionario.application.domain.Funcionario;
import br.com.gestao_funcionario.application.repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {
    private  final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        System.out.println("[Inicia] FuncionarioService - cadastrarFuncionario");
        funcionarioRepository.salvar(funcionario);
        System.out.println("[Finaliza] FuncionarioService - cadastrarFuncionario");
    }

    public List<Funcionario> buscarFuncionarios() {
        System.out.println("[Inicia] FuncionarioService - buscarFuncionarios");
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        System.out.println("[Finaliza] FuncionarioService - buscarFuncionarios");
        return  funcionarios;

    }
}
