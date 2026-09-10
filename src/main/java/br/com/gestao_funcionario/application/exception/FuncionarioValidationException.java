package br.com.gestao_funcionario.application.exception;

public class FuncionarioValidationException extends RuntimeException {
    public FuncionarioValidationException(String message) {
        super(message);
    }
}
