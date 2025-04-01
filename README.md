# **🦆 Pato's Granja API**

**API REST** desenvolvida em **Java** com **Spring Boot** para o gerenciamento completo de uma granja de patos, permitindo o **cadastro** de **patos**, **clientes** e **vendedores**, o **registro** de **vendas** com **regras de negócio**, e a **geração** de **relatórios** em **Excel**.

### Tabela de Conteúdos

1. [🦆 Pato's Granja API](#-pato's-granja-api)
2. [💻 Tecnologias](#-tecnologias)
3. [📌 Funcionalidades da API](#-funcionalidades-da-api)
4. [📜 Documentação dos Endpoints](#-documentação-dos-endpoints)
    - [🦆 Patos](#-patos)
    - [👥 Clientes](#-clientes)
    - [👨‍💼 Vendedores](#-vendedores)
    - [💸 Vendas](#-vendas)
    - [📑 Relatórios](#-relatórios)
5. [🛠️ Boas práticas adotadas](#-boas-práticas-adotadas)
6. [⚙ Como Iniciar a API](#-como-iniciar-a-api)
    - [🌀 Clonar o repositório](#-clonar-o-repositório)
    - [🐳 Rodar com Docker (recomendado)](#-rodar-com-docker-recomendado)
    - [🚀 Rodar com Maven (local)](#-rodar-com-maven-local)
    - [🔍 Acessar a API](#-acessar-a-api)
7. [🐤 Dados de exemplo (opcional)](#-dados-de-exemplo-opcional)
8. [🗓️ Cronograma completo](#-cronograma-completo)
    - [1. Preparação do Ambiente](#1-preparação-do-ambiente)
    - [2. Desenvolvimento da API](#2-desenvolvimento-da-api)
        - [2.1 Criação das Entidades e Funcionalidades](#21-criação-das-entidades-e-funcionalidades)
        - [2.2 Funcionalidades Adicionais](#22-funcionalidades-adicionais)
    - [3. Geração de Relatórios](#3-geração-de-relatórios)
    - [4. Validações, Exceções e Testes](#4-validações-exceções-e-testes)
9. [👨‍💻 Desenvolvedor](#-desenvolvedor)

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

## 📌 Funcionalidades da API

- [x]  Cadastro de **Patos** com nome, mãe, filhos e cálculo de preço conforme regras
- [x]  Cadastro de **Clientes**, incluindo opção de desconto
- [x]  Cadastro de **Vendedores** com validação de CPF e matrícula únicos
- [x]  **Registro de Vendas** com desconto automático, controle de duplicidade e data da venda
- [x]  **Listagem de Patos Vendidos** com detalhes da venda
- [x]  **Geração de Relatórios** (Excel)
- [x]  **Ranking de Vendedores** por valor total vendido com filtro por período

## **📜 Documentação dos Endpoints**

### 🦆 Patos

- `POST /patos`
    - **Descrição:** Cria um novo **Pato.**
    - **Código de Resposta:** `201 Created`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Pato filho 1",
            "maeId": 1
        }
        ```


- `PUT /patos/{id}`
    - **Descrição:** Atualiza os dados de um **Pato existente** pelo ID.
    - **Código de Resposta:** `201 Created`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Pato filho 2",
            "vendido": false
        }
        ```

- `GET /patos/{id}`
    - **Descrição:** Obtem os detalhes de um **Pato** pelo ID.
    - **Código de Resposta:** `200 Ok`
    - **Corpo da Resposta**:

        ```json
        {
            "id": 1,
            "nome": "Pato filho 1",
            "maeId": 1,
            "vendido": false
        }
        ```

- `DELETE /patos/{id}`
    - **Descrição:** Remove um **Pato** pelo ID.
    - **Código de Resposta:** `204 No Content`

### 👥 Clientes

- `POST /clientes`
    - **Descrição**: Cria um novo **Cliente.**
    - **Código de Resposta:** `200 Created`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Antônio Rodrigues",
            "elegivelParaDesconto": true
        }
        ```

- `PUT /clientes/{id}`
    - **Descrição:** Atualiza os dados de um **Cliente** **existente** pelo ID.
    - **Código de Resposta:** `201 Created`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Antônio Rodrigues",
            "elegivelParaDesconto": true
        }
        ```

- `GET /clientes/{id}`
    - **Descrição:** Obtem os detalhes de um **Cliente** pelo ID.
    - **Código de Resposta:** `200 Ok`
    - **Corpo da requisição:**

        ```json
        {
            "id": 1,
            "nome": "Antônio Rodrigues",
            "elegivelParaDesconto": true
        }
        ```

- `DELETE /clientes/{id}`
    - **Descrição:**  Remove um **Cliente** pelo ID.
    - **Código de Resposta:** `201 No Content`

### 👨‍💼 Vendedores

- `POST /vendedores`
    - **Descrição:**  Cria um novo **Vendedor.**
    - **Código de Resposta:** `201 Created`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Rodrigo Nunes",
            "cpf": "398.671.230-50",
            "matricula": "MAT41443"
        }
        ```

- `GET /vendedores`
    - **Descrição:** Obtem uma lista com todos os **Vendedores** cadastrados.
    - **Código de Resposta:** `200 Ok`
    - **Corpo da resposta:**

        ```json
        [
            {
                "id": 1,
                "nome": "Rodrigo Nunes",
                "cpf": "398.671.230-50",
                "matricula": "MAT41443"
            },
            {
                "id": 2,
                "nome": "Cláudio Silva",
                "cpf": "714.652.440-00",
                "matricula": "MAT15349"
            }
        ]
        ```

- `GET /vendedores/{id}`
    - **Descrição:** Obtem os detalhes de um **Vendedor** pelo ID.
    - **Código de Resposta:** `200 Ok`

        ```json
        {
                "id": 1,
                "nome": "Rodrigo Nunes",
                "cpf": "398.671.230-50",
                "matricula": "MAT41443"
            }
        ```

- `PUT /vendedores/{id}`
    - **Descrição:** Atualiza os dados de um **Vendedor existente** pelo ID.
    - **Código de Resposta:** `200 Ok`
    - **Corpo da requisição:**

        ```json
        {
            "nome": "Rodrigo Nunes",
            "cpf": "398.671.230-50",
            "matricula": "MAT41443"
        }
        ```

- `DELETE /vendedores/{id}`
    - **Descrição:** Remove um **Vendedor** pelo ID.
    - **Código de Resposta:** `201 No Content`

### 💸 Vendas

- `POST /vendas`
    - **Descrição:** Registra uma nova **Venda.**
    - **Código de Resposta:** `204 Created`
    - **Corpo da requisição:**

        ```json
        {
            "clienteId": 1,
            "vendedorId": 1,
            "patosIds": [
                1, 2, 3, 4
            ]
        }
        ```

- `GET /vendas/patos-vendidos`
    - **Descrição:** Obtem uma lista com todos os **Patos Vendidos.**
    - **Código de Resposta:** `200 Ok`
    - **Corpo da resposta:**

        ```json
        [
            {
                "patoId": 1,
                "nomeDoPato": "Pato filho 1.1",
                "precoUnitario": 70.00,
                "dataDaVenda": "2025-04-01T12:23:33.31077",
                "nomeDoCliente": "Antônio Rodrigues"
            },
            {
                "patoId": 2,
                "nomeDoPato": "Pato Mae 1",
                "precoUnitario": 25.00,
                "dataDaVenda": "2025-04-01T12:23:33.31077",
                "nomeDoCliente": "Antônio Rodrigues"
            }
        ]
        ```

- `GET /vendas/ranking-vendedores`
    - **Descrição:** Obtem uma lista com o ranking de Vendedores, do **maior valor total vendido** para o **menor.**
    - **Código de Resposta:** `200 Ok`
    - **Corpo da resposta:**

        ```json
        [
            {
                "lugar": 1,
                "vendedorId": 1,
                "nome": "Rodrigo Nunes",
                "totalDeVendas": 1,
                "patosVendidos": 2,
                "valorTotalVendido": 76.00
            },
            {
                "lugar": 2,
                "vendedorId": 2,
                "nome": "Cláudio Silva",
                "totalDeVendas": 1,
                "patosVendidos": 1,
                "valorTotalVendido": 56.00
            }
        ]
        ```

    - **Parâmetros opcionais:** `dataInicio`, `dataFim` - Formato ISO: `yyyy-mm-dd`
        - `GET /vendas/ranking-vendedores?dataInicio={dataInicio}&dataFim={dataFim}`

### 📑 Relatórios

- `GET /relatorios/patos`
    - **Descrição:** Gera e baixa automaticamente, pelo navegador, um relatório detalhado dos patos cadastrados e vendidos no formato `.xslx` (Excel)
    - **Código de Resposta:** `200 Ok`
    - **Headers da Resposta:**

        ```
        Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        Content-Disposition: attachment; filename="relatorio-patos.xlsx"
        ```

    - **Prévia do Relatório de Patos:**

      ![Prévia](https://github.com/kauahaymon/Desafio_Patos/blob/main/src/main/resources/static/previa_relatorio_patos.png)


## ⚠️ Erros Comuns de Validação

Caso alguma requisição falhe devido a erros de validação, a API retornará um **status HTTP 422 (Unprocessable Entity)** com uma resposta no formato JSON contendo os detalhes do erro.

### **Lista de Exceções e Erros de Validação**

### **Exceções Personalizadas**

- `EntidadeNaoEncontradaException` → Retorna **404 Not Found**
- `RegistroDuplicadoException` → Retorna **400 Bad Request**
- `PatosNaoEncontradosException` → Retorna **404 Not Found**
- `PatoVendidoException` → Retorna **409 Conflict**

### **Erros de Validação**

- `MethodArgumentNotValidException` → Retorna **422 Unprocessable Entity** com os erros de validação
- `NoResourceFoundException` → Retorna **404 Not Found** para recursos inexistentes
- `Exception` (Genérica) → Retorna **500 Internal Server Error**

### **Exemplo de JSON da Resposta de Erro**

```json
{
    "status": 422,
    "message": "Erro de validação",
    "validationErrors": [
        "Campo matrícula é obrigatório",
        "Campo nome é obrigatório",
        "CPF inválido"
    ]
}
```

## 🛠️ Boas práticas adotadas

- [x]  Padrão de projeto MVC (Model-View-Controller)
- [x]  Princípios SOLID
- [x]  Validação e tratamento global de erros (Exception Handler)
- [x]  Separação de responsabilidades (SRP)
- [x]  Uso de records para DTOs
- [x]  Mapeamento entre entidades e DTOs com **Mappers**
- [x]  Logs organizados com SLF4J

## ⚙ Como Iniciar a API

### **🌀 Clonar o repositório**

```
git clone https://github.com/kauahaymon/Desafio_Patos.git
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

Para rodar nas próximas vezes após tudo baixado, rode:

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

## 📂 Como testar a API

Você pode baixar a coleção do **Postman** para testar os endpoints da API.

**Como baixar a coleção JSON para o Postman:**

1. Clique no link abaixo para baixar o arquivo da coleção do Insomnia:

   [`Baixar coleção Postman`](https://raw.githubusercontent.com/kauahaymon/planner-springboot/main/tests/Insomnia_2024-07-15.json)

2. Importe o arquivo JSON no de preferência no **Postaman** para começar a testar os endpoints da API.

## 🐤 Dados de exemplo (opcional)

Você pode popular o banco de dados com dados fictícios para testes e visualização do sistema.

Para isso, **Copie** o script localizado em `src/main/resources/db/sample-data.sql` e o **execute** diretamente em um query em seu banco de dados Postgres.

## 🗓️ Cronograma completo

### **1. Preparação do Ambiente**

| **Atividade** | **Tempo Estimado** |
| --- | --- |
| Análise e revisão de requisitos | 2h |
| Modelagem do banco de dados | 4h |
| Configuração do ambiente de desenvolvimento | 2h |
| Criação das tabelas com Migration (Flyway) | 3h |

### **2. Desenvolvimento da API**

### **2.1 Criação das Entidades e Funcionalidades**

| **Atividade** | **Tempo Estimado** |
| --- | --- |
| Criação das entidades mapeadas | 1h |
| **Implementação das funcionalidades da API** | **6h** |
| - CRUD de Pato (com mãe, filhos, preço conforme regra) | 2h |
| - CRUD de Cliente (Nome e elegibilidade para desconto) | 2h |
| - CRUD de Vendedor (validação CPF e matrícula únicos) | 2h |

### **2.2 Funcionalidades Adicionais**

| **Atividade** | **Tempo Estimado** |
| --- | --- |
| Implementação de cadastro de vendas e regras de negócio | 4h |
| Listagem de todos os patos vendidos | 1h |
| Ranking de vendedores com maior valor total de vendas | 4h |
| Implementação de filtro por período | 2h |

### **3. Geração de Relatórios**

| **Atividade** | **Tempo Estimado** |
| --- | --- |
| **Implementação de geração de relatórios** | **6h** |
| - Ler a documentação, entender como funciona a Biblioteca Apache POI | 2h |
| - Criar a primeira implementação de relatório de Patos | 3h |
| - Separar responsabilidades e aplicar princípios SRP | 1h |

### **4. Validações, Exceções e Testes**

| **Atividade** | **Tempo Estimado** |
| --- | --- |
| Aplicar validações com Spring Validation | 1h |
| Tratar exceções com Exception Handler | 2h |
| Criar dados fictícios para popular a base de dados (Opcional) | 1h |
| Fazer testes | 2h |

## 👨‍💻 Desenvolvedor

Se você quiser saber mais sobre mim ou entrar em contato, acesse meu perfil no [LinkedIn](https://www.linkedin.com/in/kauahaymon).
