# Clojure Restful API

Projeto desenvolvido em [Clojure](https://clojure.org) utilizando o conjunto de bibliotecas do [Pedestal](http://pedestal.io).

## Objetivo

O projeto é uma API de uma lista de contatos telefônicos onde é possível guardar os números de todas as pessoas que desejar.

Cada contato telefônico deve possuir um nome único, pois este é usado como identificador na lista.

## Integrantes

Nome | RA | GitHub
------------ | ------------- | -------------
Felipe Andrade | 15.00175-0 | Kaisen-san
Willian Chan | 16.01095-7 | willianchan
Vinícius Pereira | 16.03343-4 | VinPer

## Instalação

É necessário ter o [Leiningen](https://leiningen.org) instalado no computador.

## Modo de usar

Para inicializar o projeto, aba o terminal na pasta raíz do mesmo e digite o seguinte comando:

```bash
lein run # ou 'lein repl'
```

Para fazer requisições à API recomendamos utilizar softwares como o [Postman](https://www.postman.com) ou o [Insomnia](https://insomnia.rest).

## Rotas da API

### Buscar todos os contatos

**Requisição**

- Method: ``GET``
- Endpoint: ``http://localhost:3000/contacts``

**Retorno**

- Response:
```json
[
  {
    "name": "Carlos",
    "phone": "+55 11 96655-4433"
  },
  {
    "name": "Felipe",
    "phone": "+55 11 99988-7766"
  },
  {
    "name": "Mariana",
    "phone": "+55 11 97766-5544"
  },
  {
    "name": "Vinícius",
    "phone": "+55 11 98877-6655"
  }
]
```

### Buscar contato por nome

**Requisição**

- Method: ``GET``
- Endpoint: ``http://localhost:3000/contacts?name=Felipe``
- Query params: ``name`` (nome do contato que deseja buscar)

**Retorno**

- Response:
```json
[
  {
    "name": "Felipe",
    "phone": "+55 11 99988-7766"
  }
]
```

### Criar novo contato

**Requisição**

- Method: ``POST``
- Endpoint: ``http://localhost:3000/contacts``
- Body:
```json
[
  {
    "name": "Aparecido",
    "phone": "+55 11 98765-4321"
  }
]
```

**Retorno** - Quando o nome do novo usuário não existir

- Status: ``201``
- Response:
```json
[
  {
    "name": "Aparecido",
    "phone": "+55 11 98765-4321"
  }
]
```

**Retorno** - Quando o nome do novo usuário já existir

- Status: ``400``
- Response:
```json
[
  {
    "error": "Contato com mesmo nome já adicionado."
  }
]
```

### Atualizar contato

**Requisição**

- Method: ``POST``
- Endpoint: ``http://localhost:3000/contacts?name=Aparecido``
- Query params: ``name`` (nome do contato que deseja atualizar)
- Body:
```json
[
  {
    "name": "Cido",
    "phone": "+55 11 98765-4321"
  }
]
```

**Retorno** - Quando o nome do novo usuário não existir

- Status: ``201``
- Response:
```json
[
  {
    "name": "Cido",
    "phone": "+55 11 98765-4321"
  }
]
```

**Retorno** - Quando o nome do novo usuário já existir

- Status: ``400``
- Response:
```json
[
  {
    "error": "Contato com mesmo nome já adicionado."
  }
]
```

### Deletar contato

**Requisição**

- Method: ``POST``
- Endpoint: ``http://localhost:3000/contacts?name=Cido``
- Query params: ``name`` (nome do contato que deseja deletar)

**Retorno** - Quando o nome do novo usuário existir

- Status: ``200``
- Response:
```json
[
  {
    "name": "Cido",
    "phone": "+55 11 98765-4321"
  }
]
```

**Retorno** - Quando o nome do novo usuário não existir

- Status: ``400``
- Response:
```json
[
  {
    "error": "Nenhum contato encontrado com esse nome."
  }
]
```