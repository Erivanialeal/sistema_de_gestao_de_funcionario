package br.com.gestao_funcionario.application.service;

import br.com.gestao_funcionario.application.domain.Funcionario;
import br.com.gestao_funcionario.application.repository.FuncionarioRepository;

import java.util.List;
import java.util.UUID;

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

    public Funcionario atualizaFuncionarios(UUID idFuncioanrio, Funcionario funcionarioAtualizado) {
        System.out.println("[Inicia] FuncionarioService - atualizaFuncionarios");
        Funcionario funcionario = funcionarioRepository.findById(idFuncioanrio);
        if(funcionario == null){
            throw new RuntimeException("Funcionario não encontrado");
        }
        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setDesignacao(funcionarioAtualizado.getDesignacao());
        funcionario.setSalario(funcionarioAtualizado.getSalario());
        funcionario.setTelefone(funcionarioAtualizado.getTelefone());
        funcionario.setEndereco(funcionarioAtualizado.getEndereco());
        funcionarioRepository.salvar(funcionario);
        System.out.println("[Finaliza] FuncionarioService - atualizaFuncionarios");
        return funcionario;
    }

    public void excluirTodosOsFuncionarios() {
        System.out.println("[Inicia] FuncionarioService - excluirTodosOsFuncionarios");
        funcionarioRepository.excluirTodosOsFuncionarios();
        System.out.println("[finaliza] FuncionarioService - excluirTodosOsFuncionarios");

    }

    public void excluirFuncionarioPorId(UUID idFuncionario) {

    }
}
