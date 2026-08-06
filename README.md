# Detector de Golpes

Aplicação web que usa inteligência artificial para avaliar mensagens suspeitas e indicar o risco de golpe. O usuário informa a mensagem recebida, o contexto, o possível remetente e o canal de comunicação; a API consulta o Google Gemini, pontua quatro critérios e devolve uma classificação de risco.

> **Atenção:** o resultado é uma estimativa gerada por IA e pode conter erros. Não use a aplicação como única fonte para tomar decisões financeiras ou compartilhar dados pessoais. Em caso de dúvida, confirme a mensagem diretamente pelos canais oficiais da empresa ou pessoa envolvida.

## Funcionalidades

- análise de mensagens suspeitas com Google Gemini;
- avaliação de quatro critérios, cada um com nota de `0` a `10`;
- cálculo do percentual e classificação em risco baixo, médio ou alto;
- validação dos dados enviados à API;
- tratamento padronizado de erros;
- interface responsiva em React;
- documentação interativa da API com Swagger UI;
- execução local ou por Docker Compose.

## Como a análise funciona

O backend envia ao Gemini a mensagem e as informações fornecidas pelo usuário. O modelo retorna notas para:

| Critério | O que representa |
| --- | --- |
| `incompatibilidade_contexto` | Quanto a mensagem destoa do contexto informado |
| `risco_remetente` | Indícios de remetente falso ou não confiável |
| `meio_comunicacao_oficial` | Risco de o canal não ser oficial |
| `risco_padrao_golpe` | Semelhança com padrões conhecidos de golpes |

Em todos os critérios, `0` representa nenhum indício e `10`, um indício muito forte. A média das quatro notas é convertida em percentual:

- **baixo risco:** abaixo de 40%;
- **médio risco:** de 40% a 74%;
- **alto risco:** 75% ou mais.

## Tecnologias

**Backend**

- Java 17
- Spring Boot 4.1
- Spring AI 2.0
- Google Gemini
- Spring Validation
- Springdoc OpenAPI/Swagger UI
- Maven

**Frontend**

- React 19
- Vite 8
- Axios
- Tailwind CSS 4
- daisyUI 5

## Pré-requisitos

Escolha uma das formas de execução:

- **Docker:** Docker e Docker Compose; ou
- **local:** JDK 17 ou superior e Node.js compatível com Vite 8 (recomendado: Node.js 22).

Em ambos os casos, é necessário obter uma chave da API do Google Gemini.

## Configuração

Na raiz do projeto, crie um arquivo `.env`:

```properties
GEMINI_API_KEY=sua_chave_do_gemini
```

O backend carrega esse arquivo automaticamente. Não versione a sua chave de API.

## Executando com Docker Compose

Na raiz do repositório, execute:

```bash
docker compose up
```

Depois que os serviços iniciarem, acesse:

- frontend: <http://localhost:5173>
- backend: <http://localhost:8080>
- Swagger UI: <http://localhost:8080/swagger-ui.html>

Para encerrar os contêineres:

```bash
docker compose down
```

## Executando localmente

### 1. Backend

Na raiz do projeto:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

A API ficará disponível em <http://localhost:8080>.

### 2. Frontend

Em outro terminal:

```bash
cd frontend/detector-de-golpes
npm install
npm run dev
```

A interface ficará disponível em <http://localhost:5173>.

## API

### Analisar uma mensagem

```http
POST /api/ai
Content-Type: application/json
```

Exemplo de requisição:

```json
{
  "mensagem": "Seu CPF será bloqueado hoje. Clique no link para regularizar.",
  "contexto": "Não solicitei nenhum atendimento e não tenho pendências conhecidas.",
  "empresa": "Banco Exemplo",
  "meioComunicacao": "SMS enviado por um número desconhecido"
}
```

Exemplo de resposta:

```json
{
  "incompatibilidade_contexto": 9,
  "risco_remetente": 8,
  "meio_comunicacao_oficial": 8,
  "risco_padrao_golpe": 9,
  "mensagem": "A mensagem apresenta urgência, ameaça e direcionamento para um canal não confirmado. Verifique a situação diretamente no aplicativo ou telefone oficial da instituição.",
  "porcentagem": "ALTO RISCO => Porcentagem: 85%"
}
```

As notas e o texto variam conforme a resposta do modelo.

Também é possível testar a API com `curl`:

```bash
curl -X POST http://localhost:8080/api/ai \
  -H 'Content-Type: application/json' \
  -d '{
    "mensagem": "Você ganhou um prêmio. Pague uma taxa via PIX para receber.",
    "contexto": "Não participei de nenhum sorteio.",
    "empresa": "Empresa desconhecida",
    "meioComunicacao": "WhatsApp"
  }'
```

### Erros

Erros de validação retornam HTTP `400`; falhas de comunicação com o Gemini retornam HTTP `500`. O corpo segue este formato:

```json
{
  "mensagem": "Campos faltando",
  "detalhe": "o campo de mensagem não pode estar vazio"
}
```

## Testes e verificações

Backend:

```bash
./mvnw test
```

Frontend:

```bash
cd frontend/detector-de-golpes
npm run lint
npm run build
```

## Estrutura do projeto

```text
.
├── src/main/java/...                # controllers, serviços, DTOs e erros da API
├── src/main/resources/
│   ├── application.properties       # configuração do Spring AI/Gemini
│   └── prompts/base.txt             # instruções e critérios enviados ao modelo
├── src/test/                         # testes do backend
├── frontend/detector-de-golpes/      # aplicação React/Vite
├── docker-compose.yml                # ambiente com backend e frontend
└── pom.xml                           # dependências e build Maven
```

## Observações

- O frontend está configurado para consumir a API em `http://localhost:8080`.
- O backend permite requisições do frontend local em `http://localhost:5173`.
- O modelo configurado atualmente é `gemini-3.5-flash-lite`.
- A análise usa somente os dados fornecidos e o conhecimento do modelo; ela não realiza consultas externas para confirmar remetentes ou canais oficiais.

