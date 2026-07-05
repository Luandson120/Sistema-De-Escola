# 🎓 Escola API

API REST desenvolvida com Java e Spring Boot para gerenciamento de ambiente escolar, permitindo controle de alunos, professores e autenticação de diretores com segurança baseada em JWT.

O projeto foi desenvolvido com foco em boas práticas de backend, arquitetura em camadas e organização de código utilizando o ecossistema Spring.

---

# 🚀 Tecnologias Utilizadas

## Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT Authentication
* Hibernate
* Maven

## Banco de Dados

* MySQL
* Flyway Migration

## DevOps & Ferramentas

* Docker
* Docker Compose
* Git
* GitHub
* IntelliJ IDEA
* Postman

---

# 🧠 Conceitos Aplicados

* API REST
* Arquitetura em camadas
* Autenticação com JWT
* Spring Security
* Injeção de Dependência
* Tratamento global de exceções
* Validações customizadas
* Persistência com JPA/Hibernate
* Versionamento de banco com Flyway
* Containerização com Docker

---

# 📂 Estrutura do Projeto

```bash id="5cv92a"
src/main/java/escola/escola_api
│
├── config
├── controller
├── dto
├── exception
├── model
├── repository
├── security
├── service
└── validation
```

### Organização das Camadas

* `controller` → Endpoints da API
* `service` → Regras de negócio
* `repository` → Comunicação com banco de dados
* `model` → Entidades da aplicação
* `security` → JWT e autenticação
* `validation` → Validações customizadas
* `exception` → Tratamento global de erros

---

# 🔐 Segurança

A aplicação utiliza autenticação baseada em JWT (JSON Web Token) com integração ao Spring Security.

Funcionalidades implementadas:

* Login autenticado
* Geração de token JWT
* Filtro de autenticação
* Proteção de rotas
* UserDetailsService customizado

---

# 📌 Funcionalidades

## 👨‍🎓 Alunos

* Cadastro de alunos
* Atualização de dados
* Busca de registros
* Exclusão de alunos

## 👨‍🏫 Professores

* Cadastro de professores
* Gerenciamento de informações

## 👔 Diretores

* Autenticação segura
* Controle de acesso

---

# ✅ Validações

O projeto possui validação customizada de CPF utilizando annotation própria:

```java id="g5z4fi"
@CPFValido
```

Além disso:

* Tratamento global de exceções
* Respostas padronizadas de erro
* Validação de entrada de dados

---

# 🗄️ Banco de Dados

O versionamento do banco é realizado com Flyway.

Scripts de migration:

* Criação da tabela de pessoas
* Criação de alunos
* Criação de professores
* Criação de diretores

---

# 🐳 Docker

O projeto possui configuração Docker para facilitar execução e ambiente de desenvolvimento.

Arquivo disponível:

```bash id="4yz0pw"
docker-compose.yml
```

---

# ⚙️ Como Executar o Projeto

## Pré-requisitos

* Java 17+
* Maven
* Docker (opcional)
* MySQL

---

## Clone o repositório

```bash id="m7y3ta"
git clone https://github.com/Luandson120/Sistema-De-Escola.git
```

---

## Configure o banco de dados

No arquivo:

```bash id="8yr9ik"
src/main/resources/application.properties
```

Configure:

```properties id="r6z8ki"
spring.datasource.url=jdbc:mysql://localhost:3306/escola
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

## Execute o projeto

```bash id="m8k0wd"
mvn spring-boot:run
```

---

# 📬 Endpoints Principais

## Autenticação

```http id="m2j5qw"
POST /auth/login
```

## Alunos

```http id="1jk8fe"
GET    /alunos
POST   /alunos
PUT    /alunos/{id}
DELETE /alunos/{id}
```

## Professores

```http id="p9v2lx"
GET    /professores
POST   /professores
```

---

# 📈 Objetivo do Projeto

Este projeto foi desenvolvido com o objetivo de consolidar conhecimentos em desenvolvimento backend com Java e Spring Boot, aplicando conceitos utilizados em aplicações reais, como autenticação, segurança, persistência de dados e arquitetura de software.

---

# 👨‍💻 Autor

Luandson Duarte

* Formado em Sistemas de Informação
* Desenvolvedor Backend
* Foco em Java e Spring Boot

GitHub:
https://github.com/Luandson120

LinkedIn:
[www.linkedin.com/in/luandson-duarte-145296255](http://www.linkedin.com/in/luandson-duarte-145296255)

---

# 📄 Licença

Este projeto está sob a licença MIT.
