package br.com.gestao_funcionario.application.exception;

public class FuncionarioNotFoundException extends RuntimeException {
    public  FuncionarioNotFoundException(String mensagem){
        super(mensagem);
    }
}
