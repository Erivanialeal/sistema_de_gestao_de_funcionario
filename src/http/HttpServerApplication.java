

import br.com.gestao_funcionario.application.api.FuncionarioController;
import br.com.gestao_funcionario.application.api.FuncionarioEndpoint;
import br.com.gestao_funcionario.application.repository.FuncionarioRepository;
import br.com.gestao_funcionario.application.repository.FuncionarioRepositoryMap;
import br.com.gestao_funcionario.application.service.FuncionarioService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApplication {

    public static void main(String[] args) throws IOException {
        FuncionarioRepository repository = new FuncionarioRepositoryMap();
        FuncionarioService service = new FuncionarioService(repository);
        FuncionarioController controller = new FuncionarioController(service);
        FuncionarioEndpoint endpoint = new FuncionarioEndpoint(controller);
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext(
                "/funcionarios",
                endpoint::atender
        );
        server.start();

        System.out.println("Servidor iniciado em http://localhost:8080");
    }
}