package br.com.gestao_funcionario.application.service;

import br.com.gestao_funcionario.application.domain.Funcionario;
import br.com.gestao_funcionario.application.exception.FuncionarioNotFoundException;
import br.com.gestao_funcionario.application.exception.FuncionarioValidationException;
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

        if(funcionario.getNome() == null || funcionario.getNome().isBlank()){
            throw new FuncionarioValidationException(" O nome do funcionario é obrigatorio! ");}

        if(funcionario.getDesignacao() == null || funcionario.getDesignacao().isBlank()){
            throw new FuncionarioValidationException(" A Designação  do funcionario é obrigatorio! ");}

        if(funcionario.getSalario() == null || funcionario.getSalario().isBlank()){
            throw new FuncionarioValidationException(" O sálario  do funcionario é obrigatorio! ");}

        if(funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()){
            throw new FuncionarioValidationException(" O telefone  do funcionario é obrigatorio! ");}

        if(funcionario.getEndereco() == null || funcionario.getEndereco().isBlank()){
            throw new FuncionarioValidationException(" O endereço  do funcionario é obrigatorio! ");}

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
            throw new FuncionarioNotFoundException("Funcionario não encontrado");
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
        System.out.println("[Inicia] FuncionarioService - excluirFuncionarioPorId");
        funcionarioRepository.excluirFuncionarioPorId(idFuncionario);
        System.out.println("[finaliza] FuncionarioService - excluirFuncionarioPorId");

    }
}
