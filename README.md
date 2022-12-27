**GFT - APIs**

Objetivo: aplicar os conceitos referente a API RESTful (interface de programação de aplicações em conformidade com as restrições da arquitetura REST), que foram abordados durante os treinamentos do projeto Starter da GFT Brasil.

**⚒ FERRAMENTAS UTILIZADAS**

- Ambiente de desenvolvimento Eclipse IDE

- Spring Tools 4 para Eclipse

- MySQL Community Server

- HeidiSQL

- Postman

**📘 FRAMEWORKS / TECNOLOGIAS / BIBLIOTECAS**

- Spring Boot, Spring MVC, Spring Security, Spring Validation, Spring Dev Tools e Spring Data JPA)

- Javatuples

- Lombok

- Model Mapper

- Hibernate

- Rest Template (utilizado para o consumo de API externa)

- Linguagem de programação: Java


**📦 ESTRUTURA DO PROJETO**

-As classes deste projeto estão organizadas em pacotes, de acordo com o papel de cada uma delas como componentes das APIs.

🔹 O pacote com.gft.projeto.entities contém as classes que representam entidades em nossa aplicação;

🔹 O pacote [...]repositories contém interfaces que estendem a JpaRepository, com a finalidade de facilitar a parte de persistência de dados da aplicação, quando utilizamos esse framework o mesmo já traz alguns métodos prontos para persistirmos os dados da nossa aplicação;

🔹 O pacote [...]services contém a implementação dos métodos de CRUD (Create, Read, Update e Delete) além de algumas operações que contém as regras de negócio da aplicação;

🔹 O pacote [...]dto contém classes para transporte de informações, mais especificamente do gerenciamento da montadora em questão, visando controlar vendas, peças, veículos, fornecedores, entre outros pontos importantes do controle da aplicação. 

🔹 O pacote [...]controller contém os controllers da aplicação, que vão receber as requisições feitas pelo usuário e devolver para ele respostas.

**:warning: IMPORTANTE**

-O diretório nomeado **"Dump",** contém scripts das tabelas perfil e usuário do banco de dados para agilizar os testes que exigem autenticação na aplicação.


**💻 Autoria do código:**

Ana Beatriz Andrade - ana.andrade@gft.com 📧

Ana Beatriz Cirino - ana.cirino@gft.com 📧

Fernanda Cardinaly - fernanda.silva@gft.com 📧

Karen Escobedo - karen.escobedo@gft.com 📧

