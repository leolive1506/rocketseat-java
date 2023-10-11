# [Criar estrutura do projeto spring](https://start.spring.io/)

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
## Objeto  (Model)
- Model

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