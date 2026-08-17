# contatos-api

Este repositório contém a API de contatos.

## Executando com Docker Compose

1. Coloque o arquivo do banco de dados SQLite no diretório `./database` (crie a pasta se necessário) e renomeie-o para:

   `base_consolidada.db`

2. Defina qual arquivo o container deve usar criando um arquivo `.env` na raiz do projeto com o conteúdo:

   `SQLITE_DB_NAME=base_consolidada.db`

   (Alternativa rápida: export `SQLITE_DB_NAME=base_consolidada.db` antes de executar o compose.)

3. Executar o Compose (usa o arquivo `compose.yaml`):

   `docker compose -f compose.yaml up --build -d`

4. Parar/remover containers:

   `docker compose -f compose.yaml down`

5. Ver logs:

   `docker compose -f compose.yaml logs -f`

Observação: o compose mapeia `./database` para `/data` dentro do container e a aplicação usa o caminho `/data/${SQLITE_DB_NAME}` para abrir o SQLite.

## Notas rápidas

- Certifique-se de que o arquivo `compose.yaml` existe na raiz do projeto (o repositório já inclui `compose.yaml` que mapeia `./database` para `/data`).
- Se preferir rodar sem detaching, remova `-d` do comando `up`.
- Nomeie o .env com o nome do db, no .env.exemple mostra como de modo prático.

