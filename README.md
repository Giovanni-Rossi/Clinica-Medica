# Clínica Médica API

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar uma clínica médica, é um projeto baseado para o desenvolvimento de minhas habilidades. A aplicação lida com operações relacionadas a **médicos**, **clientes** e, futuramente, **consultas**. Atualmente, implementamos os controladores para gerenciar **clientes** e **médicos**, enquanto o controlador para **consultas** está previsto para desenvolvimento futuro.

## Sumário

- [Recursos](#recursos)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Endpoints Disponíveis](#endpoints-disponíveis)
  - [Clientes](#clientes)
  - [Médicos](#médicos)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Futuras Melhorias](#futuras-melhorias)

## Recursos

- Gerenciamento de **clientes** (criação, leitura, atualização e exclusão).
- Gerenciamento de **médicos** com CRUD completo e filtro por especialidade.
- Estrutura preparada para a adição de gerenciamento de **consultas**.
- Validação básica de JSON para requisições.
- Respostas HTTP apropriadas para sucesso e erro.

## Tecnologias Utilizadas

- Java 8+
- [Spring Boot](https://spring.io/projects/spring-boot)
- Spring MVC (para criação de endpoints REST)
- Jackson (para manipulação de JSON)
- Dependências do Maven/Gradle para gerenciamento do projeto

## Endpoints Disponíveis

### Clientes
  * Listar todos os clientes
     * **URL:** ``GET /clientes``
     * **Resposta:** Lista de objetos Cliente ou 404 se não houver clientes.
  * Obter cliente por ID
      * **URL:** ``GET /clientes/{id}``
      * **Parâmetros de caminho:** id - ID do cliente
      * **Resposta:** Objeto Cliente correspondente ou 404 se não encontrado.

 * Criar um novo cliente

    * **URL:** ``POST /clientes``
    * **Corpo da requisição:** Objeto JSON representando o cliente.

```json
{
  "id": 1,
  "email": "exemplo@dominio.com",
  "senha": "senhaSegura",
  "cpf": "12345678900",
  "nome": "Nome do Cliente",
  "telefone": "11999999999",
  "sexo": "Masculino/Feminino",
  "dataNascimento": "YYYY-MM-DD",
  "papel": "papelDoCliente"
}
```
  * **Resposta**: Objeto Cliente criado ou erro 400/422 em caso de falha.

* Atualizar um cliente existente
  * **URL:** ``PUT /clientes/{id}``
  * **Parâmetros de caminho:** id - ID do cliente a ser atualizado
  * Corpo da requisição: Objeto JSON com os novos dados do cliente.
  * Resposta: Objeto Cliente atualizado ou 404 se não encontrado; erros 400/422 se aplicável.

* Remover um cliente
  * **URL:** ``DELETE /clientes/{id}``
  * **Parâmetros de caminho:** id - ID do cliente a ser removido
  * **Resposta:** Status 204 (No Content) em sucesso ou 404 se o cliente não for encontrado.

### Médicos

* Listar todos os médicos
  * **URL:** ``GET /medicos``
  * **Resposta:** Lista de objetos Medico ou 404 se não houver médicos cadastrados.

* Obter médico por ID
  * **URL:** ``GET /medicos/{id}``
  * Parâmetros de caminho: id - ID do médico
  * Resposta: Objeto Medico correspondente ou 404 se não encontrado.
    
* Listar médicos por especialidade
  * **URL:** ``GET /medicos/especialidade/{especialidade}``
  * **Parâmetros de caminho:** especialidade - Especialidade médica para filtrar
  * **Resposta:** Lista de objetos Medico que correspondem à especialidade ou 404 se nenhum for encontrado.

* Criar um novo médico
  * **URL:** ``POST /medicos``
  * **Corpo da requisição:** Objeto JSON representando o médico.
  ``` json
  {
  "id": 1,
  "email": "medico@dominio.com",
  "senha": "senhaSegura",
  "CRM": "12345",
  "especialidade": "Cardiologia",
  "nome": "Nome do Médico",
  "papel": "papelDoMedico"
  }
  ```
  * ** Resposta:** Objeto Medico criado ou erro 400/422 em caso de falha.
* Atualizar um médico existente
  * **URL:** ``PUT /medicos/{id}``
  * **Parâmetros de caminho:** id - ID do médico a ser atualizado
  * **Corpo da requisição:** Objeto JSON com os novos dados do médico.
  * **Resposta:** Objeto Medico atualizado ou 404 se não encontrado; erros 400/422 se aplicável.

* Remover um médico
  * **URL:** ``DELETE /medicos/{id}``
  * **Parâmetros de caminho:** id - ID do médico a ser removido
  * **Resposta:** Status 204 (No Content) em sucesso ou 404 se o médico não for encontrado.

* Consultas
Consultas: Estrutura prevista, mas ainda não implementada. Futuramente, incluirá agendamento, atualização e cancelamento de consultas.

### Como Executar o Projeto
Pré-requisitos:

* Java 8 ou superior
    
* Maven
    
* Banco de dados configurado (se aplicável; pode ser configurado via application.properties)
  
Passos para Executar
  1. Clone o repositório:
```bash
git clone https://github.com/Giovanni-Rossi/Clinica-Medica

cd clinica
```

  3. Compile e execute a aplicação:
```bash
  mvn spring-boot:run
```

# Futuras Melhorias
  * ~**Implementação de Endpoints para Consultas:** Expandir a API para incluir gerenciamento completo de consultas (agendamento, atualização e cancelamento).~
  * **Validações e Tratamento de Erros:** Melhorar a validação de entrada e os tratamentos de erros para todas as rotas.
  * **Autenticação e Autorização:** Implementar segurança para proteger os endpoints.
