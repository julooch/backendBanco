Projeto back-end de transações bancárias

## Descrição
Este é um projeto de backend desenvolvido em Java usando o framework Spring Boot. O objetivo do projeto é gerenciar transferências bancárias entre usuários MERCHANT E COMMON com duas entidades principais: Usuário (`User`) e Transaction (`Transaction`). Além disso, o sistema possui validações entre usuários e que envia notificações.

## Arquitetura
A arquitetura do projeto segue o padrão MVC (Model-View-Controller), dividida nas seguintes camadas:

- **Controller:** Gerencia as requisições HTTP e mapeia os endpoints da API.
- **Service:** Contém a lógica de negócios.
- **Repository:** Interage com o banco de dados.
- **DTOs:** Data Transfer Objects para transferência de dados entre as camadas.
- **Domain:** Entidades que representam as tabelas do banco de dados.
- **Infra:** Configurações e exceções.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Abstração de persistência de dados.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Maven**: Gerenciamento de dependências e build.

## Funcionalidades
- **Gerenciamento de Usuários:** Cadastro, consulta e listagem de usuários.
- **Gerenciamento de Transações:** Criação de transações entre usuários, com validações de saldo e tipo de usuário.
- **Notificações:** Envio de notificações para os usuários envolvidos nas transações.

## Como Executar
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/desafio-backend.git
