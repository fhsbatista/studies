# Guia de Validação - Kafka Connect

## Checklist de Implementação

✅ **Passo 1**: Dependências JPA e drivers adicionados
✅ **Passo 2**: Serviços Docker (PostgreSQL, MySQL, Kafka Connect) configurados
✅ **Passo 3**: Configurações JPA e PostgreSQL no application.properties
✅ **Passo 4**: Consumer modificado para persistir no PostgreSQL
✅ **Passo 5**: Configuração Kafka Connect JDBC Source criada
✅ **Passo 6**: Configuração Kafka Connect JDBC Sink criada
✅ **Passo 7**: Scripts de configuração dos conectores criados

## Como Validar

### 1. Iniciar os serviços

```bash
docker compose up -d
```

Aguarde alguns minutos para o Kafka Connect instalar o plugin JDBC. Você pode verificar os logs:

```bash
docker logs -f kafka-connect
```

Procure por: `Installed component kafka-connect-jdbc`

### 2. Verificar se os serviços estão rodando

```bash
docker ps
```

Você deve ver: zookeeper, kafka, postgres, mysql, kafka-connect, kafbat-ui, app

### 3. Verificar se o Kafka Connect está pronto

```bash
curl http://localhost:8083/connectors
```

Deve retornar: `[]` (array vazio significa que está pronto)

### 4. Registrar os conectores

```bash
cd scripts
./setup-connectors.sh
```

Ou manualmente:

```bash
# Source
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @scripts/connect-source-config.json

# Sink
curl -X POST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d @scripts/connect-sink-config.json
```

### 5. Verificar status dos conectores

```bash
# Listar conectores
curl http://localhost:8083/connectors

# Status do Source
curl http://localhost:8083/connectors/postgres-message-source/status

# Status do Sink
curl http://localhost:8083/connectors/mysql-message-sink/status
```

Os status devem mostrar `"state":"RUNNING"` para ambos.

### 6. Executar a aplicação

A aplicação já está configurada para enviar mensagens automaticamente. Se estiver rodando via Docker, verifique os logs:

```bash
docker logs -f app
```

Ou execute localmente:

```bash
./gradlew bootRun
```

### 7. Verificar dados no PostgreSQL

```bash
docker exec -it postgres psql -U kafka_user -d kafka_db -c "SELECT * FROM message ORDER BY id;"
```

### 8. Verificar tópico criado pelo Kafka Connect

```bash
# Via Kafka UI (http://localhost:8080)
# Ou via kafka-console-consumer
docker exec -it kafka kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic postgres-message \
  --from-beginning
```

### 9. Verificar dados no MySQL (replicados)

```bash
docker exec -it mysql mysql -u kafka_user -pkafka_pass kafka_db -e "SELECT * FROM message ORDER BY id;"
```

## Fluxo Esperado

1. **Producer** envia mensagem (string) → Tópico `teste-groups`
2. **Consumer** recebe mensagem → Transforma em `Message` → Salva no **PostgreSQL**
3. **Kafka Connect Source** observa PostgreSQL → Publica no tópico `postgres-message`
4. **Kafka Connect Sink** consome `postgres-message` → Replica no **MySQL**

## Troubleshooting

### Kafka Connect não inicia
- Verifique os logs: `docker logs kafka-connect`
- Aguarde a instalação do plugin JDBC (pode levar 2-5 minutos)

### Conectores falham
- Verifique se os bancos estão acessíveis: `docker exec -it postgres pg_isready`
- Verifique as credenciais nos arquivos de configuração
- Verifique os logs: `docker logs kafka-connect | grep ERROR`

### Dados não aparecem no MySQL
- Verifique se o Source está rodando: `curl http://localhost:8083/connectors/postgres-message-source/status`
- Verifique se há dados no tópico: `docker exec -it kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic postgres-message --from-beginning`
- Verifique se o Sink está rodando: `curl http://localhost:8083/connectors/mysql-message-sink/status`

### Tabela não existe
- O Hibernate deve criar automaticamente com `spring.jpa.hibernate.ddl-auto=update`
- Para MySQL, o Sink tem `auto.create=true` que deve criar a tabela

