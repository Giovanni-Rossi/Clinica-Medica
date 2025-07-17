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
### Como Rodar com Kubernetes (Minikube & Helm)
Esta seção descreve como implantar a aplicação em um ambiente Kubernetes local.

**Dependências Adicionais**  
Para este modo de execução, você precisará das seguintes ferramentas:

- **Minikube**: Para criar o cluster Kubernetes local.
- **kubectl**: A ferramenta de linha de comando para interagir com o cluster.
- **Helm**: O gerenciador de pacotes para o Kubernetes.

**Roteiro de Instalação e Execução**  
Siga os passos abaixo para configurar o ambiente, construir as imagens e implantar a aplicação.

1. **Iniciar o Cluster Kubernetes**

   Inicie o Minikube para criar o seu cluster local e ative o addon ingress, que é necessário para o acesso externo à aplicação.

   ```bash
   # Inicia o cluster Minikube
   minikube start

   # Ativa o Ingress Controller
   minikube addons enable ingress
   ```

2. **Construir as Imagens da Aplicação**

   O projeto inclui um script que automatiza a construção das imagens Docker do backend e do frontend, carregando-as diretamente no ambiente do Minikube.

   ```bash
   # Dê permissão de execução para o script (apenas na primeira vez)
   chmod +x build.sh

   # Execute o script para construir as imagens
   ./build.sh
   ```

3. **Instalar a Aplicação com Helm**

   Com as imagens prontas e o cluster no ar, utilize o Helm para implantar todos os componentes da aplicação (banco de dados, backend e frontend).

   ```bash
   # Instala a aplicação usando o Helm Chart contido na pasta ./clinica-chart
   helm install clinica-release ./clinica-chart
   ```

   Após a instalação, aguarde cerca de um minuto para que todos os contêineres sejam iniciados. Você pode verificar o status com o comando:

   ```bash
   kubectl get all
   ```

   Espere até que todos os Pods na lista estejam com o status **Running**.

**Acesso à Aplicação via Kubernetes**  
Para que seu navegador consiga encontrar a aplicação através da URL [http://k8s.local](http://k8s.local), você precisa associar o IP do seu cluster Minikube a este domínio.

a. **Encontre o IP do Minikube**

   Execute o seguinte comando no seu terminal:

   ```bash
   minikube ip
   ```

b. **Edite seu arquivo hosts**

   Você precisará de permissões de administrador para editar este arquivo.

   - **No Windows (usando WSL):** Edite o arquivo do Windows.

     Abra o Bloco de Notas (ou outro editor) como Administrador.  
     Abra o arquivo: `C:\Windows\System32\drivers\etc\hosts`

     Adicione a seguinte linha no final do arquivo (substitua `IP_DO_MINIKUBE` pelo IP que você obteve no passo anterior):

     ```
     IP_DO_MINIKUBE k8s.local
     ```

   - **No Linux ou macOS:**

     Execute o comando:

     ```bash
     sudo nano /etc/hosts
     ```

     Adicione a seguinte linha no final do arquivo:

     ```
     IP_DO_MINIKUBE k8s.local
     ```

c. **Acesse no Navegador**
Agora, abra seu navegador de preferência e acesse a URL:

http://k8s.local
