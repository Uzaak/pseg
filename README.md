# Demo Project

Demo Project

## Preparações

### MySQL

Após clonar o projeto, o arquivo em /src/main/resources/sql/run_this.sql contém os códigos para a criação das tabelas utilizadas pelo sistema

Para rodar o arquivo, conectar ao MySQL local e executar:

```
mysql> source {path_to_project}/src/main/resources/sql/run_this.sql
```

### Unix Terminal

O script em /src/main/resources/sh/curl.sh foi criado para testar rapidamente via terminal todas as requisições feitas pelo sistema.

```
$ {path_to_project}/src/main/resources/sh/curl.sh
```