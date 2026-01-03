 Kafka Connect - Configuração e Uso

Este diretório contém os arquivos de configuração e scripts para configurar o Kafka Connect com PostgreSQL e MySQL.

## Arquivos

- `connect-source-config.json`: Configuração do conector JDBC Source que observa a tabela `message` no PostgreSQL
- `connect-sink-config.json`: Configuração do conector JDBC Sink que replica dados para o MySQL
- `setup-connectors.sh`: Script para registrar os conectores no Kafka Connect

## Como Usar

### 1. Baixar o driver MySQL (OBRIGATÓRIO)

Antes de iniciar os serviços, você precisa baixar o driver MySQL:

```bash
./download-mysql-driver.sh
```

Isso criará o diretório `drivers/` na raiz do projeto com o driver MySQL necessário.

### 2. Iniciar os serviços

```bash
docker-compose up -d
```

Aguarde alguns segundos para todos os serviços iniciarem, especialmente o Kafka Connect que precisa instalar o plugin JDBC e copiar o driver MySQL.

### 2. Verificar se o Kafka Connect está pronto

```bash
curl http://localhost:8083/connectors
```

Se retornar `[]`, o Kafka Connect está pronto.

### 3. Registrar os conectores

Execute o script de setup a partir do diretório `scripts`:

```bash
cd scripts
./setup-connectors.sh
```

Ou registre manualmente:

**Source (PostgreSQL):**
```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @connect-source-config.json
```

**Sink (MySQL):**
```bash
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @connect-sink-config.json
```

### 4. Verificar status dos conectores

```bash
# Listar conectores
curl http://localhost:8083/connectors

# Status do Source
curl http://localhost:8083/connectors/postgres-message-source/status

# Status do Sink
curl http://localhost:8083/connectors/mysql-message-sink/status
```

### 5. Testar o fluxo

1. Execute a aplicação Spring Boot (ela enviará mensagens automaticamente)
2. O Consumer receberá as mensagens e salvará no PostgreSQL
3. O Kafka Connect Source observará as mudanças no PostgreSQL e publicará no tópico `postgres-message`
4. O Kafka Connect Sink consumirá do tópico e replicará no MySQL

### 6. Resetar ou Deletar Tópico

Se você precisar começar do zero ou resetar os offsets:

**Opção 1: Deletar o tópico completamente**
```bash
cd scripts
./delete-topic.sh
# Depois recrie os conectores
./setup-connectors.sh
```

**Opção 2: Resetar offsets do conector (ler desde o início)**
```bash
cd scripts
./reset-connector-offsets.sh
```

### 7. Verificar dados

**PostgreSQL:**
```bash
docker exec -it postgres psql -U kafka_user -d kafka_db -c "SELECT * FROM message;"
```

**MySQL:**
```bash
docker exec -it mysql mysql -u kafka_user -pkafka_pass kafka_db -e "SELECT * FROM message;"
```

## Troubleshooting

- Se os conectores falharem, verifique os logs: `docker logs kafka-connect`
- Certifique-se de que o plugin JDBC foi instalado corretamente
- Verifique se as tabelas foram criadas nos bancos de dados
- Confirme que o Kafka está acessível pelo Kafka Connect

