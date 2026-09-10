# Sistema de Gestão de Funcionários

API para gerenciamento de funcionários desenvolvida em **Java puro**, sem utilização de frameworks web como Spring Boot.

O projeto foi desenvolvido com o objetivo de praticar conceitos fundamentais de desenvolvimento backend, como **separação de responsabilidades, arquitetura em camadas, contratos de API, tratamento de exceções, validação e comunicação HTTP**.

## 🎯 Objetivo

O sistema permite realizar operações de gerenciamento de funcionários:

* Cadastrar funcionário
* Consultar funcionários
* Atualizar funcionário
* Excluir funcionário por ID
* Excluir todos os funcionários
* Validar dados obrigatórios
* Tratar erros de entrada e recursos inexistentes

## 🛠️ Tecnologias utilizadas

* Java 21
* Maven
* Jackson
* Java HTTP Server (`com.sun.net.httpserver.HttpServer`)
* Postman
* Git/GitHub

## 🏗️ Arquitetura

O projeto foi estruturado separando as responsabilidades de cada camada:

**Domain**

Responsável pela representação dos dados do funcionário.

**API**

Define os contratos das operações e realiza a comunicação entre a camada HTTP e as regras da aplicação.

**Service**

Responsável pelas regras de negócio e validações.

**Repository**

Define a abstração para acesso aos dados.

**RepositoryMap**

Implementa a persistência utilizando `Map<UUID, Funcionario>` em memória, simulando o comportamento de um banco de dados.

**Endpoint**

Responsável pela comunicação HTTP, leitura das requisições, interpretação dos dados recebidos e construção das respostas HTTP.

**Exception**

Contém as exceções específicas utilizadas para representar erros de validação e recursos não encontrados.

## 💾 Persistência

Neste projeto não foi utilizado um banco de dados tradicional.

O armazenamento foi implementado utilizando um `HashMap`:

```java
Map<UUID, Funcionario>
```

O UUID do funcionário funciona como chave e o objeto `Funcionario` como valor.

Essa abordagem foi utilizada para praticar a separação entre a regra de negócio e a forma como os dados são armazenados.

Através da interface `FuncionarioRepository`, a aplicação não fica diretamente dependente do `HashMap`, permitindo que futuramente a implementação seja substituída por uma persistência real.

## 🌐 Endpoints

### Cadastrar funcionário

```http
POST /funcionarios
```

Exemplo de requisição:

```json
{
  "nome": "Maria",
  "designacao": "Desenvolvedora",
  "salario": "5000",
  "telefone": "61999999999",
  "endereco": "Brasilia"
}
```

**Resposta de sucesso:**

```text
HTTP 201 Created
```

### Consultar funcionários

```http
GET /funcionarios
```

**Resposta de sucesso:**

```text
HTTP 200 OK
```

### Atualizar funcionário

```http
PUT /funcionarios/{id}
```

Exemplo:

```http
PUT /funcionarios/550e8400-e29b-41d4-a716-446655440000
```

**Resposta de sucesso:**

```text
HTTP 200 OK
```

### Excluir funcionário por ID

```http
DELETE /funcionarios/{id}
```

**Resposta de sucesso:**

```text
HTTP 204 No Content
```

### Excluir todos os funcionários

```http
DELETE /funcionarios
```

**Resposta de sucesso:**

```text
HTTP 204 No Content
```

## ⚠️ Tratamento de erros

A aplicação possui tratamento para diferentes situações:

| Situação                    |            HTTP |
| --------------------------- | --------------: |
| Requisição válida           | 200 / 201 / 204 |
| Dados obrigatórios ausentes |             400 |
| ID em formato inválido      |             400 |
| Funcionário não encontrado  |             404 |
| Método HTTP não suportado   |             405 |

Exemplo de erro de validação:

```text/plain
  O nome do funcionário é obrigatório!
```

Exemplo de funcionário não encontrado:

```text
Funcionário não encontrado
```

## ▶️ Como executar

### Pré-requisitos

* Java 21 ou superior
* Maven
* IDE de sua preferência

### Executando o projeto

Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

Entre no diretório:

```bash
cd <NOME_DO_PROJETO>
```

Compile o projeto:

```bash
mvn clean install
```

Execute a classe:

```text
HttpServerApplication
```

O servidor será iniciado na porta:

```text
http://localhost:8080
```

A API poderá então ser testada utilizando o Postman.

## 🧪 Testes com Postman

As operações podem ser testadas diretamente pelo Postman utilizando as rotas disponibilizadas pela aplicação.

Exemplos de cenários testados:

* Cadastro com dados válidos
* Cadastro com campos obrigatórios vazios
* Consulta dos funcionários
* Atualização de funcionário existente
* Atualização de funcionário inexistente
* Exclusão de funcionário existente
* Exclusão de funcionário inexistente
* Exclusão utilizando UUID inválido
* Exclusão de todos os funcionários

## 📚 Conceitos praticados

Este projeto foi desenvolvido com foco no aprendizado e aplicação prática de:

* Programação orientada a objetos
* Interfaces
* Injeção manual de dependências
* Separação de responsabilidades
* Arquitetura em camadas
* Encapsulamento
* UUID
* Collections
* `Map` como mecanismo de persistência
* Exceções customizadas
* Validação de dados
* Comunicação HTTP
* Métodos HTTP
* Status codes
* JSON
* Serialização e desserialização
* Maven
* Testes de API com Postman

Projeto desenvolvido para aprofundar conhecimentos em **Java Backend**, principalmente sobre como as responsabilidades de uma aplicação são organizadas quando não existe um framework realizando essas abstrações automaticamente.

A implementação manual do servidor HTTP permitiu compreender na prática o fluxo de uma requisição:

```text
Cliente
   ↓
HTTP
   ↓
Endpoint
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Map
```
