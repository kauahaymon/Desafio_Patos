# **🦆 Pato's Granja API**

**API REST** desenvolvida em **Java** com **Spring Boot** para o gerenciamento completo de uma granja de patos, permitindo o **cadastro** de **patos**, **clientes** e **vendedores**, o **registro** de **vendas** com **regras de negócio**, e a **geração** de **relatórios** em **Excel**.

## 💻 Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA / Hibernate
- Spring Data Validation
- PostgreSQL
- Apache POI (relatórios Excel)
- Flyway (Migration)
- Lombok
- Docker

## 📊 Funcionalidades da API

- [x]  Cadastro de **Patos** com nome, mãe, filhos e cálculo de preço conforme regras
- [x]  Cadastro de **Clientes**, incluindo opção de desconto
- [x]  Cadastro de **Vendedores** com validação de CPF e matrícula únicos
- [x]  **Registro de Vendas** com desconto automático, controle de duplicidade e data da venda
- [x]  **Listagem de Patos Vendidos** com detalhes da venda
- [x]  **Geração de Relatórios** (Excel)
- [x]  **Ranking de Vendedores** por valor total vendido com filtro por período

## 🧠 Boas práticas adotadas

- [x]  Padrão de projeto MVC (Model-View-Controller)
- [x]  Princípios SOLID
- [x]  Validação e tratamento global de erros (Exception Handler)
- [x]  Separação de responsabilidades (SRP)
- [x]  Uso de records para DTOs
- [x]  Mapeamento entre entidades e DTOs com **Mappers**
- [x]  Logs organizados com SLF4J

## **📜 Documentação dos Endpoints**

### Patos

- `POST /patos` - Cria um novo **Pato.**
- `GET /patos/{id}` - Obtem os detalhes de um **Pato** pelo ID.
- `PUT /patos/{id}` - Atualiza os dados de um **Pato existente** pelo ID.
- `DELETE /patos/{id}` - Remove um **Pato** pelo ID.

### Clientes

- `POST /clientes` - Cria um novo **Cliente.**
- `GET /clientes/{id}` - Obtem os detalhes de um **Cliente** pelo ID.
- `PUT /clientes/{id}` - Atualiza os dados de um **Cliente** **existente** pelo ID.
- `DELETE /clientes/{id}` - Remove um **Cliente** pelo ID.

### Vendedores

- `POST /vendedores`- Cria um novo **Vendedor.**
- `GET /vendedores` - Obtem uma lista com todos os **Vendedores** cadastrados.
- `GET /vendedores/{id}` - Obtem os detalhes de um **Vendedor** pelo ID.
- `PUT /vendedores/{id}` - Atualiza os dados de um **Vendedor existente** pelo ID.
- `DELETE /vendedores/{id}` - Remove um **Vendedor** pelo ID.

### Vendas

- `POST /vendas` - Registra uma nova **Venda.**
- `GET /vendas/patos-vendidos` - Obtem uma lista com todos os **Patos Vendidos.**
- `GET /vendas/ranking-vendedores` - Obtem uma lista com o ranking de Vendedores, do **maior valor total vendido** para o **menor.**
    - Parâmetros opcionais: `dataInicio`, `dataFim` - Formato ISO: `yyyy-mm-dd`
    - `GET /vendas/ranking-vendedores?dataInicio={dataInicio}&dataFim={dataFim}`

### Relatórios

- `GET /relatorios/patos` - Gera e baixa automaticamente um relatório detalhado dos patos cadastrados e vendidos no formato `.xslx` (Excel)

## ⚙ Como Iniciar a API

### **🌀 Clonar o repositório**

```
https://github.com/kauahaymon/Desafio_Patos.git
```

**Navegue até o Diretório do Projeto**:

```
cd Desafio_Patos
```

### **🐳 Rodar com Docker (recomendado)**

Certifique-se de ter Docker **instalado** e **rodando.** Não tem docker? **[Docker Download](https://www.docker.com/products/docker-desktop/)**

Rode o comando abaixo na pasta raiz do projeto:

```
docker-compose up --build
```

O comando acima irá criar a imagem **Docker** e subir os contairners do **Banco Postgres** e da **Aplicação.**

Na primeira vez, ele irá baixar os arquivos necessários, aguarde alguns segundos.

Para roda nas próximas vezes após tudo baixado, rode:

```
docker-compose up
```

A aplicação será executada em: [`http://localhost:8080`](http://localhost:8080)

### **🚀 Rodar com Maven (local)**

Pre requisitos:

- Ter **Java 21** instalado (`java -version`)
- Ter **Maven** instalado (`mvn -version`)
- Ter o **PostgreSQL** rodando localmente
1. Acesse seu PostgreSQL pelo **pgAdmin** ou pelo **Terminal**:

   Pelo **terminal**:

    ```
    psql -U postgres
    ```

   **Senha**: postgres

2. Crie o banco `patos_database` manualmente.

    ```sql
    CREATE DATABASE patos_database;
    ```

3. Rode o projeto:

    ```
    ./mvnw spring-boot:run
    ```


### 🔍 Acessar a API

- A aplicação será executada em: [**`http://localhost:8080`**](http://localhost:8080)

## 🐤 Dados de exemplo (opcional)

Você pode popular o banco de dados com dados fictícios para testes e visualização do sistema.

Para isso, **Copie** o script localizado em `src/main/resources/db/sample-data.sql` e o **execute** diretamente em um query em seu banco de dados Postgres.

## 🗓️ Cronograma completo

### Preparando o ambiente

1. Análise e revisão de requisitos - 2h
2. Modelagem do banco de dados - 4h
3. Configuração do ambiente de desenvolvimento - 2h
4. Criação das tabelas com Migration(Flyway) - 3h

### Durante o Desenvolvimento

1. Criação das entidades mapeadas - 1h
2. Implementação das funcionalidades da API - 6h
    1. CRUD de Pato (com mãe, filhos, preço conforme regra). - 2h
    2. CRUD de Cliente (Nome e elegibilidade para desconto) - 2h
    3. CRUD de Vendedor (validação CPF e matrícula únicos). - 2h
3. Implementação de cadastro de vendas e regras de négocio - 4h
4. Listagem de todos os patos vendidos - 1h
5. Ranking de vendedores com maior Valor Total de Vendas - 4h
6. Implementação de filtro por período - 2h
7. Implementação de geração de relatórios - 6h
    1. Ler a documentação, entender como funciona a Biblioteca Apache POI - 2h
    2. Criar a primeira implementação de relatório de Patos - 3h
    3. Seperar responsabilidades e aplicar princípios SRP - 1h
8. Aplicar validações com Spring Validation - 1h
9. Tratar exções com Exception Handler- 2h
10. Criar dados fictícios para popular a base de dados (Opcional) - 1h
11. Fazer testes - 2h

## 👨‍💻 Desenvolvedor

Se você quiser saber mais sobre mim ou entrar em contato, acesse meu perfil no [LinkedIn](https://www.linkedin.com/in/kauahaymon).