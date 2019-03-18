# Demo Project

Demo Project

## Preparações

### MySQL

Após clonar o projeto, o arquivo em /src/main/resources/sql/run_this.sql contém os códigos para a criação das tabelas utilizadas pelo sistema

Para rodar o arquivo, conectar ao MySQL local e executar:

```
mysql> source {path_to_project}/src/main/resources/sql/run_this.sql
```

## Quick Start

O script em /src/main/resources/sh/curl.sh foi criado para testar rapidamente via terminal todas as requisições feitas pelo sistema.

```
$ {path_to_project}/src/main/resources/sh/curl.sh
```

## Casos de uso pedidos na prova

### 1 - Cadastrar um produto

```
curl -X POST \
  http://localhost:8080/pseg/products \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 8649d857-e330-4ebc-823e-f4d04c508ce0' \
  -H 'cache-control: no-cache' \
  -d '{
	"description": "Super Mario Odyssey",
	"price": 249.00
}'
```

### 2 - Cadastrar um cliente

```
curl -X POST \
  http://localhost:8080/pseg/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 4b6462ba-cabb-498b-a9e4-882d7c0f010d' \
  -H 'cache-control: no-cache' \
  -d '{
	"name": "John Appleseed",
	"cpf": 99999999999,
	"email": "johnnyblack@sao.com",
	"password": "Link Start"
}'
```
*Obs: A senha é salva criptografada, mas é retornada em plain-text para facilitar a visualização durante a avaliação

### 3 - Fazer uma compra usando um cartão de crédito com um produto cadastrado.

Compra utilizando cartão cadastrado na wallet (via cardId):
```
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 07c80bff-20b6-444d-bb1d-87796aa2a303' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardId": 1,
	"productId": 1
}'
```
*Obs: O cartão é salvo criptografado, mas é retornado em plain-text para facilitar a visualização durante a avaliação

Compra utilizando cartão não salvo (soma dos dígitos par, portanto transação é bem sucedida e cartão é salvo ao final):
```
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 46d13614-1144-44fd-8d8b-d77cc6c67836' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardNumber": 22,
	"creditCardHolder": "John Bla Bla",
	"creditCardCvv": 123,
	"creditCardExpiration": "2020-12-12",
	"productId": 1
}'
```
*Obs: O cartão é salvo criptografado, mas é retornado em plain-text para facilitar a visualização durante a avaliação

Compra utilizando cartão não salvo (soma dos dígitos ímpar, portanto transação não é bem sucedida e cartão não é salvo):
```
curl -X POST \
  http://localhost:8080/pseg/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: c101a7b8-00a2-41cd-beb4-eed046dc356d' \
  -H 'cache-control: no-cache' \
  -d '{
	"userId": 1,
	"creditCardNumber": 21,
	"creditCardHolder": "John Bla Bla",
	"creditCardCvv": 123,
	"creditCardExpiration": "2020-12-12",
	"productId": 1
}'
```
*Obs: O cartão é salvo criptografado, mas é retornado em plain-text para facilitar a visualização durante a avaliação

### 4 - Consultar produtos de um cliente (pagos e não pagos)

Consultar todos os produtos:
```
curl -X GET \
  http://localhost:8080/pseg/users/1/transactions \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 76a922f8-4214-4d1d-86ba-5fb0ac1de372' \
  -H 'cache-control: no-cache'
```

Consultar todos os produtos pagos:
```
curl -X GET \
  'http://localhost:8080/pseg/users/1/transactions?paid=true' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 51ef7984-bac0-4964-8c88-1ca1e6c6e164' \
  -H 'cache-control: no-cache'
```

Consultar todos os produtos não pagos:
```
curl -X GET \
  'http://localhost:8080/pseg/users/1/transactions?paid=false' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0a81367b-dc1b-454e-9dc9-163faa434e19' \
  -H 'cache-control: no-cache'
```

### 5 - Buscar os cartões do clientes

```
curl -X GET \
  http://localhost:8080/pseg/users/1/cards \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0113862d-eb0d-49fb-899f-c4ac67397e38' \
  -H 'cache-control: no-cache'
```

### 6 - Criar / editar produto

Criar (mesmo caso que o nº 1):
```
curl -X POST \
  http://localhost:8080/pseg/products \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 8649d857-e330-4ebc-823e-f4d04c508ce0' \
  -H 'cache-control: no-cache' \
  -d '{
	"description": "Super Mario Odyssey",
	"price": 249.00
}'
```

Editar:
```
curl -X PUT \
  http://localhost:8080/pseg/products \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 9afc67c4-f122-431a-95bd-6c52d6e4fcf6' \
  -H 'cache-control: no-cache' \
  -d '{
	"id": 1,
	"description": "Super Mario Odyssey for Nintendo Switch",
	"price": 249.00
}'
```