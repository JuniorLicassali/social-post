# Social Post

# Sobre o projeto

SocialPost é uma REST API nível 3 de maturidade Richardson, com o intuito de ser consumida por um Front end construído por um amigo de tempos de Graduação.
A API consiste em fazer publicações com descrições e imagem, além de buscar postagens e fazer comentários.

Desenvolvida seguindo as melhores práticas do mercado, esta API parte do básico, como os CRUDs, e vai muito além, com tópicos avançados como:
- Segurança: JWT, Oauth2
- Upload de Arquivos e serviço de Armazenagem em nuvem como Amazon S3
- HATEOAS
- Documentação com Swagger
- Testes de Integração
- Migração e Versionamento de Banco de Dados: Flyway

## Modelo conceitual
![Modelo Conceitual](https://github.com/JuniorLicassali/social-post/blob/main/images/SocialPost%20-%20Diagrama%20de%20classes.png)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven

# Como executar o projeto

## Back end
Pré-requisitos: Java 17 e Banco de dados MySql

```bash
# clonar repositório
git clone https://github.com/JuniorLicassali/social-post

# entrar na pasta do projeto back end
cd social-post

# executar o Maven para baixar as dependências e construir o projeto
./mvn clean install

# executar o projeto
./mvnw spring-boot:run
```

## Banco de dados
Certifique-se de que você tenha o MySQL instalado em seu ambiente local. Caso não tenha, você pode baixá-lo [aqui](https://dev.mysql.com/downloads/installer/).

Crie um banco de dados chamado socialpost. Se você preferir, pode configurar o Spring Boot para criar o banco automaticamente, utilizando as seguintes configurações no arquivo application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost/socialpost?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
```
Observação: A aplicação possui uma massa de dados que exclui tudo no banco antes de injetar informações, como usuários para teste, postagens e comentários. Isso facilita a execução e os testes do sistema com dados pré-configurados.

Se você quiser alterar qualquer coisa na massa de dados, basta acessar o arquivo que fica em `src/main/resources/db/testdata/afterMigrate.sql`

## Documentação da API

A documentação da API pode ser acessada via Swagger na URL: http://127.0.0.1:8080/swagger-ui/index.html

## Endpoints

Para uma melhor compreensão dos endpoints disponíveis na API, recomenda-se acessar a documentação via Swagger no link acima.

Além disso, você pode acessar a aplicação diretamente em [http://localhost:8080/](http://localhost:8080/) sem saber o caminho correto dos recursos. A API possui linkagem através do HATEOAS, permitindo uma navegação fácil e intuitiva, sem a necessidade de memorizar as URLs dos diferentes recursos.

## Autenticação

A API oferece dois tipos de fluxo de autenticação: **Client Credentials** e **Authorization Code**.

## Client Credentials

Para usar o fluxo de Client Credentials:

1. No Postman ou Insomnia, faça um POST para a seguinte URL: http://localhost:8080/oauth2/token

2. Utilize a autenticação Basic Auth e passe as seguintes credenciais:
- **Username**: `socialpost-backend`
- **Password**: `backend123`

![Authorization CC](https://github.com/JuniorLicassali/social-post/blob/main/images/Authorization_CC.jpg)

3. Após a requisição, copie o `access_token` retornado e utilize-o nas requisições seguintes como um Bearer Token.

![Body CC](https://github.com/JuniorLicassali/social-post/blob/main/images/Body_CC.jpg)

## Authorization Code

Para usar o fluxo de Authorization Code:

1. Acesse a seguinte URL no seu navegador: http://localhost:8080/oauth2/authorize?response_type=code&client_id=socialpostweb&state=abc&redirect_uri=http://127.0.0.1:8080/authorized&scope=READ%20WRITE&code_challenge=bKE9UspwyIPg8LsQHkJaiehiTeUdstI5JZOvaoQRgJA&code_challenge_method=S256

2. Ao aparecer a tela de login, preencha os campos:
- **Email**: `email@example.com`
- **Senha**: `123`

![Login ac](https://github.com/JuniorLicassali/social-post/blob/main/images/login_AC.jpg)

3. Aceite os escopos solicitados.

![Scope ac](https://github.com/JuniorLicassali/social-post/blob/main/images/scope_AC.jpg)

4. Copie da URL o código gerado no campo `code`
![Code ac](https://github.com/JuniorLicassali/social-post/blob/main/images/code_AC.jpg)

5. Com o código copiado, faça um POST para a seguinte URL: http://localhost:8080/oauth2/token

    Utilize a autenticação Basic Auth e passe as seguintes credenciais:
    - **Username**: `socialpostweb`
    - **Password**: `web123`
    
    No corpo da requisição, passe os seguintes parâmetros:
    - **grant_type**: `authorization_code`
    - **code**: `codePegoDaUrl` (substitua `codePegoDaUrl` pelo código copiado)
    - **redirect_uri**: `http://127.0.0.1:8080/authorized`
    - **code_verifier**: `abc123`

  ![Code ac](https://github.com/JuniorLicassali/social-post/blob/main/images/token_AC.jpg)

Após realizar esses passos, o `access_token` obtido poderá ser utilizado para autenticar suas requisições.

# Autor

Emanoel F. Licassali

https://www.linkedin.com/in/emanoel-licassali-793604228
