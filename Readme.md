# Clínica App

Aplicação full-stack para gerenciamento de clínica, composta por:

- **Backend**: Aplicação Spring Boot (localizada em `clinica`)
- **Frontend**: Aplicação React (localizada em `frontend`)
- **Banco de Dados**: PostgreSQL

## Dependências

- Docker
- Docker Compose

## Como Rodar

1. Certifique-se de que o Docker e o Docker Compose estão instalados e em execução.
2. Na raiz do projeto (onde está o arquivo `docker-compose.yml`), execute o comando abaixo para construir e inicializar os containers:

    ```bash
    docker-compose up --build
    ```

    - **backend**: Disponível em [http://localhost:8080](http://localhost:8080)
    - **frontend**: Disponível em [http://localhost:3000](http://localhost:3000)
    - **postgres**: Banco de dados utilizado pelo backend

## Notas

- No arquivo `docker-compose.yml`, a variável de ambiente `REACT_APP_API_URL` do frontend está configurada para `http://backend:8080`.
- Para visualizar os logs dos containers, utilize:

    ```bash
    docker-compose logs -f
    ```
