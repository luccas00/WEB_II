# Frontend

Aplicação React que consome os serviços de **usuários**, **eventos**,
**vendas** e **notificações** expostos pelo gateway.

## Configuração de porta

A porta utilizada para acessar o gateway pode ser definida através do
arquivo `.env` (`VITE_API_PORT`) ou pela tela de *Configuração* no próprio
site. Um exemplo de arquivo pode ser encontrado em `.env.example`.

## Scripts

- `npm run dev` – inicia o servidor de desenvolvimento
- `npm run build` – gera os arquivos de produção
- `npm run lint` – executa o ESLint
