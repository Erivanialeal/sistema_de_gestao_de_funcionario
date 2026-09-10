package br.com.gestao_funcionario.application.api;
import br.com.gestao_funcionario.application.domain.Funcionario;
import br.com.gestao_funcionario.application.exception.FuncionarioValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FuncionarioEndpoint {
    public static final String FUNCIONARIOS = "/funcionarios";

    private final FuncionarioController funcionarioController;
    private  final ObjectMapper objectMapper;

    public FuncionarioEndpoint(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
        this.objectMapper = new ObjectMapper();
    }

    public void atender(HttpExchange exchange) throws IOException {

        String metodo = exchange.getRequestMethod();
        String caminho = exchange.getRequestURI().getPath();


        if ("POST".equalsIgnoreCase(metodo)) {
            cadastrar(exchange);
            return;
        }

        if ("GET".equalsIgnoreCase(metodo)) {
            buscarFuncionarios(exchange);
            return;
        }

        if("PUT".equalsIgnoreCase(metodo)){
            atualizarFuncionario(exchange);
            return;
        }
        if ("DELETE".equalsIgnoreCase(metodo)) {

            if (caminho.equals("/funcionarios")) {
                excluir(exchange);
                return;
            }

            excluirFuncionarioPorId(exchange);
            return;
        }

        exchange.sendResponseHeaders(405, -1);
        exchange.close();
    }

    public void cadastrar(HttpExchange exchange) throws IOException {
        try {
            String body =new String(
                    exchange.getRequestBody().readAllBytes(),
                    StandardCharsets.UTF_8
            );

            JsonNode json = objectMapper.readTree(body);
            UUID idFuncionario = UUID.randomUUID();

            String nome = json.get("nome").asText();
            String designacao = json.get("designacao").asText();
            String salario = json.get("salario").asText();
            String telefone = json.get("telefone").asText();
            String endereco= json.get("endereco").asText();

            Funcionario funcionario = new Funcionario(
                    idFuncionario,
                    nome,
                    designacao,
                    salario,
                    telefone,
                    endereco
            );

            funcionarioController.cadastrarFuncionarios(funcionario);
            String resposta = objectMapper.writeValueAsString(funcionario);
            exchange.getResponseHeaders()
                    .set("Content-Type","application/json");

            exchange.sendResponseHeaders(201, resposta.getBytes(StandardCharsets.UTF_8).length);
            exchange.close();

        }catch (FuncionarioValidationException e){
            String resposta = e.getMessage();
            exchange.getResponseHeaders()
                    .set("Content-Type", "text/plain; charset=UTF-8");

            exchange.sendResponseHeaders(
                    400,resposta.getBytes(StandardCharsets.UTF_8).length
            );
            exchange.getResponseBody()
                    .write(resposta.getBytes(StandardCharsets.UTF_8));

            exchange.close();
        }
    }

    public void buscarFuncionarios(HttpExchange exchange) throws IOException{
        List<Funcionario> funcionarios = funcionarioController.buscarFuncionarios();

        String resposta = objectMapper.writeValueAsString(funcionarios);

        exchange.getResponseHeaders()
                .set("Content-Type", "application/json");

        exchange.sendResponseHeaders(
                200,resposta.getBytes(StandardCharsets.UTF_8).length
        );
        exchange.getResponseBody()
                .write(resposta.getBytes(StandardCharsets.UTF_8));
        exchange.close();
    }

    public  void atualizarFuncionario(HttpExchange exchange) throws IOException{
       String caminho =exchange.getRequestURI().getPath();
       String id = caminho.substring(caminho.lastIndexOf("/")+ 1);
       UUID idFuncionario = UUID.fromString(id);

       String body = new String(
               exchange.getRequestBody().readAllBytes(),
               StandardCharsets.UTF_8
       );

        JsonNode json = objectMapper.readTree(body);

        String nome = json.get("nome").asText();
        String designacao = json.get("designacao").asText();
        String salario = json.get("salario").asText();
        String telefone = json.get("telefone").asText();
        String endereco = json.get("endereco").asText();

        Funcionario funcionarioAtualizado = new Funcionario(
                null,
                nome,
                designacao,
                salario,
                telefone,
                endereco
        );

        Funcionario funcionario = funcionarioController.atualizarFuncionarios(
                idFuncionario,
                funcionarioAtualizado
        );
        String resposta =
                objectMapper.writeValueAsString(funcionario);

        exchange.getResponseHeaders()
                .set("Content-Type", "application/json");

        exchange.sendResponseHeaders(
                200,
                resposta.getBytes(StandardCharsets.UTF_8).length
        );
        exchange.getResponseBody()
                .write(resposta.getBytes(StandardCharsets.UTF_8));

        exchange.close();
    }

    public  void excluir(HttpExchange exchange)throws  IOException{
        String requets = exchange.getRequestMethod();
        funcionarioController.excluirTodosOsFuncionarios();
        exchange.sendResponseHeaders(204,-1);
        exchange.close();

    }

    public  void excluirFuncionarioPorId(HttpExchange exchange) throws IOException{
        String caminho =exchange.getRequestURI().getPath();
        String id = caminho.substring(caminho.lastIndexOf("/")+ 1);
        UUID idFuncionario = UUID.fromString(id);
        funcionarioController.excluirFuncionarioPorId(idFuncionario);
        exchange.sendResponseHeaders(204,-1);
        exchange.close();

    }
}
