# Clínica App

Aplicação full-stack para gerenciamento de clínica, capaz de realizar operações de CRUD para Medicos e Clientes. É composta por:

- **Backend**: Aplicação Spring Boot (localizada em `clinica`)
- **Frontend**: Aplicação React (localizada em `frontend`)
- **Banco de Dados**: PostgreSQL

## Sobre a aplicação
A aplicação permite duas operações de CRUD(Create Read Update and Delete) sobre duas classes, cliente e medico. A motivação da aplicação é o reuso do back-end criado na disciplina de WEB-1 e a escolha do React framework no front-end pelo seu uso em WEB-2.

## Estrutura do Projeto

```plaintext
.
├── clinica/           # Backend Spring Boot
│   └── Dockerfile
├── frontend/          # App React
│   └── Dockerfile
├── docker-compose.yml
├── README.md          # Descrição completa da atividade
```

## Dependências

- Docker
- Docker Compose

## Como Rodar

1. Certifique-se de que o Docker e o Docker Compose estão instalados e em execução.
2. Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute o comando abaixo para construir e inicializar os containers:

    ```bash
    docker-compose up --build
    ```
## Acesso

Para acessar a aplicação, abra seu navegador e digite o endereço: [http://localhost:3000](http://localhost:3000). A interface é responsiva e intuitiva, permitindo fácil navegação e interação com todas as funcionalidades do sistema.

## Notas

- No arquivo `docker-compose.yml`, a variável de ambiente `REACT_APP_API_URL` do frontend está configurada para `http://backend:8080`.
- Para visualizar os logs dos containers, utilize:

    ```bash
    docker-compose logs -f
    ```
