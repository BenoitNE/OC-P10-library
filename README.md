# OCR Library

**Benoit Nerin**

## Outils et technologies utilisés

IDE - Spring Tool Suite 4

JDK - 1.8

Apache Maven 3.6.3

spring-boot 2.3.3.RELEASE

### ocr-library-api

1. spring-boot-starter-data-jpa
2. spring-boot-starter-web
3. hibernate-search-orm
4. h2

### ocr-library-batch

1. spring-boot-starter-mail
2. spring-boot-starter-data-jpa
3. mysql-connector-java
4. spring-cloud-starter-openfeign

### ocr-library-web

1. validation-api
2. spring-boot-starter-web
3. spring-boot-starter-thymeleaf
4. spring-cloud-starter-openfeign
5. spring-boot-starter-security

## Lancement

**API**
Executer ocr-library-api `Runs As > Spring Boot App`
URL: http://localhost:9090

**Web**
Executer ocr-library-web `Runs As > Spring Boot App`
URL: http://localhost:8080/login

Login de demo:
- Nom d'utilisateur: invite
- Mot de passe: invite

## Aperçus

![login](https://github.com/BenoitNE/ocr-library/blob/master/login.png)
![loans](https://github.com/BenoitNE/ocr-library/blob/master/loans.png)