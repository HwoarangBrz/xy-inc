[![Build Status](https://travis-ci.org/HwoarangBrz/xy-inc.svg?branch=master)](https://travis-ci.org/HwoarangBrz/xy-inc)

#API de  Pontos de interesse POI

API de sistema de pontos de interesse com Java e Spring Boot

### Detalhes da API RESTful
A API RESTful de POI contém as seguintes características:  
* Projeto criado com Spring Boot e Java 8
* Banco de dados MySQL com JPA e Spring Data JPA
* Migração de banco de dados com Flyway
* Testes unitários e de integração com JUnit e Mockito
* Integração contínua com TravisCI

### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
```
git clone https://github.com/HwoarangBrz/xy-inc.git
cd poi
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080/swagger-ui.html
```
### Importando o projeto no Eclipse ou STS
No terminal, execute a seguinte operação:
```
mvn eclipse:eclipse
```
No Eclipse/STS, importe o projeto como projeto Maven.
	