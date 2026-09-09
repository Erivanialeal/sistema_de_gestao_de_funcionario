package br.com.gestao_funcionario.application.api;

import br.com.gestao_funcionario.application.domain.Funcionario;
import br.com.gestao_funcionario.application.service.FuncionarioService;

import java.util.List;
import java.util.UUID;

public class FuncionarioController implements FuncionarioAPI {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Override
    public void cadastrarFuncionarios(Funcionario funcionario) {
        System.out.println("[Inicia] FuncionarioController - cadastro Funcionario");
        funcionarioService.cadastrarFuncionario(funcionario);
        System.out.println("[Finaliza] FuncionarioController - cadastro Funcionario");
    }

    @Override
    public List<Funcionario>buscarFuncionarios() {
        System.out.println("[Inicia] FuncionarioController - buscarFuncionarios");
        List<Funcionario> funcionarios = funcionarioService.buscarFuncionarios();
        System.out.println("[finaliza] FuncionarioController - buscarFuncionarios");
        return  funcionarios;
    }

    @Override
    public Funcionario atualizarFuncionarios(UUID idFuncionario, Funcionario funcionarioAtualizado) {
        System.out.println("[Inicia] FuncionarioController - atualizarFuncionarios");
        Funcionario funcionario = funcionarioService.atualizaFuncionarios(idFuncionario, funcionarioAtualizado);
        System.out.println("[finaliza] FuncionarioController - atualizarFuncionarios");
        return funcionario;

    }

    @Override
    public void excluir(UUID idFuncionarios) {

    }
}
