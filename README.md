# [Criar estrutura do projeto spring](https://start.spring.io/)
# Run app maven
```sh
mvn spring-boot:run
```

# Estrutura
- target
  - onde projeto é compilado
- .src
  - main
    - static
      - arquivo application.properties (.ENV)
      - templates, paginas html, layouts
# Spring boot
- popular por facilitar configurações
  - ja tem as dependencias que gerencia essas configurações

# CONCEITOS
- As classes estão dentro de um package
- toda anotation tem uma função a ser executada
- A partir da class principal, percorre as outras pastas e arquivos. Funcionando de forma recursiva
  - por isso os controllers ficam dentro da estrutura da classe principal
- @SpringBootApplication
  - essa anotation define que a classe TodolistApplication é a classe inicial do projeto
- Modificador -> niveis de acesso (public, private, protected)
- interface define os metodos mas não tem sua implementação

## Response entity


## Controller
- Um componente
- Camada entre a requisição e as demais camadas das regras de negocios
- Criar uma rota, path
- Criar
  - @Controller
    - Estrutura onde tenha paginas
    - Não retorna somente objeto, string
  - @RestController
    - Usado para API
- Criar rota
  - @RequestMapping("primeiraRota")
  - @GetMapping("/")

# Lombok
- Facilita getters e setters
```java
import lombok.Data;
// getters e setters para todos atributos
@Data
// apenas getter
@Getter
// apenas setter
@Setter
```

# Banco
- spring data jpa 
  - usa conceito de ORM
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
```java
@Data
@Entity(name = "tb_users")
public class UserModel {

  @Id
  // Gerar automaticamente
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String username;
  private String name;
  private String password;
}

```
## h2
- banco de dados em memória

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```
```env
# acessar rota console -> localhost:8080/h2-console

spring.datasource.url=jdbc:h2:mem:todolist
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

# devtools (live reload)
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <optional>true</optional>
</dependency>
```

# Login
- [Site render](https://render.com/)
- Criar docker file
```Dockerfile
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
```