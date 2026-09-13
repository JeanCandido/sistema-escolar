# 🎓 Sistema de Gerenciamento Acadêmico

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de professores, cursos e alunos. O projeto utiliza **Spring Data JPA**, **Hibernate** e **H2 Database**, aplicando DTOs, validações e relacionamentos entre entidades.

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- Bean Validation

## 📋 Funcionalidades

- Cadastro e consulta de professores
- Cadastro e consulta de cursos
- Cadastro e consulta de alunos
- Associação de cursos a professores
- Associação de alunos a cursos
- Validação dos dados enviados pela API
- Utilização de DTOs para requisições e respostas
- Persistência de dados com JPA/Hibernate
- API REST

## 🔗 Relacionamentos

```text
Professor 1 ─────── N Curso 1 ─────── N Aluno
```

- Um professor pode possuir vários cursos.
- Um curso pertence a um professor.
- Um curso pode possuir vários alunos.
- Um aluno pertence a apenas um curso.

## ⚙️ Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/JeanCandido/sistema-escolar.git
```

### 2. Entre na pasta do projeto

```bash
cd SEU-REPOSITORIO
```

### 3. Execute o projeto com Maven

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou execute a classe principal do projeto pela sua IDE.

A API estará disponível em:

```text
http://localhost:8080
```

## 🗄️ Banco de dados H2

O projeto utiliza o banco de dados **H2** durante o desenvolvimento.

Caso o console H2 esteja habilitado, ele pode ser acessado em:

```text
http://localhost:8080/h2-console
```

As configurações de conexão devem ser consultadas no arquivo:

```text
src/main/resources/application.properties
```

## 📡 Endpoints

### 👨‍🏫 Professores

#### Listar professores

```http
GET /professor
```

Exemplo de resposta:

```json
[
  {
    "idProfessor": 1,
    "nome": "Carlos Silva",
    "email": "carlos@email.com",
    "departamento": "Tecnologia",
    "cursos": [
      {
        "idCurso": 1,
        "nome": "Sistemas de Informação"
      }
    ]
  }
]
```

#### Cadastrar professor

```http
POST /professor
Content-Type: application/json
```

Exemplo:

```json
{
  "nome": "Carlos Silva",
  "email": "carlos@email.com",
  "departamento": "Tecnologia"
}
```

---

### 📚 Cursos

#### Listar cursos

```http
GET /curso
```

Exemplo de resposta:

```json
[
  {
    "idCurso": 1,
    "nome": "Sistemas de Informação",
    "professor": {
      "idProfessor": 1,
      "nome": "Carlos Silva",
      "email": "carlos@email.com",
      "departamento": "Tecnologia"
    }
  }
]
```

#### Cadastrar curso

```http
POST /curso
Content-Type: application/json
```

Exemplo:

```json
{
  "nome": "Sistemas de Informação",
  "idProfessor": 1
}
```

O `idProfessor` deve corresponder a um professor já cadastrado.

---

### 👨‍🎓 Alunos

#### Listar alunos

```http
GET /aluno
```

Exemplo de resposta:

```json
[
  {
    "idAluno": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "curso": {
      "idCurso": 1,
      "nome": "Sistemas de Informação"
    }
  }
]
```

#### Cadastrar aluno

```http
POST /aluno
Content-Type: application/json
```

Exemplo:

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "idCurso": 1
}
```

O `idCurso` deve corresponder a um curso já cadastrado.

## 🧪 Fluxo de cadastro

Para criar os relacionamentos corretamente, recomenda-se seguir esta ordem:

### 1. Criar um professor

```http
POST /professor
```

```json
{
  "nome": "Carlos Silva",
  "email": "carlos@email.com",
  "departamento": "Tecnologia"
}
```

### 2. Criar um curso associado ao professor

```http
POST /curso
```

```json
{
  "nome": "Sistemas de Informação",
  "idProfessor": 1
}
```

### 3. Criar um aluno associado ao curso

```http
POST /aluno
```

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "idCurso": 1
}
```

É possível cadastrar vários alunos utilizando o mesmo `idCurso`.

## 📁 Estrutura do projeto

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── unasp/
    │           └── projeto_spring/
    │               ├── controllers/
    │               │   ├── AlunoController.java
    │               │   ├── CursoController.java
    │               │   └── ProfessorController.java
    │               │
    │               ├── dtos/
    │               │   ├── AlunoRequestDTO.java
    │               │   ├── AlunoResponseDTO.java
    │               │   ├── CursoRequestDTO.java
    │               │   ├── CursoResponseDTO.java
    │               │   ├── CursoSimplesDTO.java
    │               │   ├── ProfessorRequestDTO.java
    │               │   ├── ProfessorResponseDTO.java
    │               │   └── ProfessorSimplesDTO.java
    │               │
    │               ├── entitys/
    │               │   ├── Aluno.java
    │               │   ├── Curso.java
    │               │   └── Professor.java
    │               │
    │               ├── repositories/
    │               │   ├── AlunoRepository.java
    │               │   ├── CursoRepository.java
    │               │   └── ProfessorRepository.java
    │               │
    │               ├── services/
    │               │   ├── AlunoService.java
    │               │   ├── CursoService.java
    │               │   └── ProfessorService.java
    │               │
    │               └── ProjetoSpringApplication.java
    │
    └── resources/
        └── application.properties
```

## 🏗️ Arquitetura

O projeto utiliza uma separação em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller

Responsável por receber as requisições HTTP e retornar as respostas da API.

### Service

Contém as regras de negócio e realiza a conversão entre entidades e DTOs.

### Repository

Responsável pela comunicação com o banco de dados através do Spring Data JPA.

### Entity

Representa as tabelas e os relacionamentos do banco de dados.

### DTO

Define quais dados são recebidos e retornados pela API, evitando expor diretamente as entidades JPA.

## 🎯 Objetivo

O projeto foi desenvolvido para praticar conceitos de **desenvolvimento de APIs REST com Spring Boot**, incluindo persistência de dados, arquitetura em camadas, DTOs, validação e relacionamentos **One-to-Many** e **Many-to-One** com JPA.