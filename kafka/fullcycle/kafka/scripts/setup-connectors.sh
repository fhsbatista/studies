#!/bin/bash

# Script para configurar os conectores do Kafka Connect
# Execute este script após iniciar os containers do docker-compose

# Obter o diretório do script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

KAFKA_CONNECT_URL="http://localhost:8083"

echo "Aguardando Kafka Connect estar pronto..."
MAX_RETRIES=30
RETRY_COUNT=0

until curl -s "$KAFKA_CONNECT_URL/connectors" > /dev/null 2>&1; do
  if [ $RETRY_COUNT -ge $MAX_RETRIES ]; then
    echo "Erro: Kafka Connect não está respondendo após $MAX_RETRIES tentativas"
    exit 1
  fi
  echo "Aguardando Kafka Connect... (tentativa $((RETRY_COUNT + 1))/$MAX_RETRIES)"
  sleep 5
  RETRY_COUNT=$((RETRY_COUNT + 1))
done

echo "Kafka Connect está pronto!"

# Verificar se os arquivos de configuração existem
if [ ! -f "connect-source-config.json" ]; then
  echo "Erro: connect-source-config.json não encontrado"
  exit 1
fi

if [ ! -f "connect-sink-config.json" ]; then
  echo "Erro: connect-sink-config.json não encontrado"
  exit 1
fi

# Registrar o conector Source (PostgreSQL)
echo "Registrando conector Source (PostgreSQL)..."
RESPONSE=$(curl -s -w "\n%{http_code}" -X POST "$KAFKA_CONNECT_URL/connectors" \
  -H "Content-Type: application/json" \
  -d @connect-source-config.json)

HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
BODY=$(echo "$RESPONSE" | sed '$d')

if [ "$HTTP_CODE" -eq 201 ] || [ "$HTTP_CODE" -eq 409 ]; then
  if [ "$HTTP_CODE" -eq 409 ]; then
    echo "Conector Source já existe. Atualizando..."
    curl -s -X PUT "$KAFKA_CONNECT_URL/connectors/postgres-message-source/config" \
      -H "Content-Type: application/json" \
      -d @connect-source-config.json | grep -o '"name":"[^"]*"' || echo "Conector atualizado"
  else
    echo "Conector Source registrado com sucesso!"
  fi
else
  echo "Erro ao registrar conector Source. HTTP Code: $HTTP_CODE"
  echo "$BODY"
fi

echo ""

# Aguardar um pouco antes de registrar o Sink
sleep 3

# Registrar o conector Sink (MySQL)
echo "Registrando conector Sink (MySQL)..."
RESPONSE=$(curl -s -w "\n%{http_code}" -X POST "$KAFKA_CONNECT_URL/connectors" \
  -H "Content-Type: application/json" \
  -d @connect-sink-config.json)

HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
BODY=$(echo "$RESPONSE" | sed '$d')

if [ "$HTTP_CODE" -eq 201 ] || [ "$HTTP_CODE" -eq 409 ]; then
  if [ "$HTTP_CODE" -eq 409 ]; then
    echo "Conector Sink já existe. Atualizando..."
    curl -s -X PUT "$KAFKA_CONNECT_URL/connectors/mysql-message-sink/config" \
      -H "Content-Type: application/json" \
      -d @connect-sink-config.json | grep -o '"name":"[^"]*"' || echo "Conector atualizado"
  else
    echo "Conector Sink registrado com sucesso!"
  fi
else
  echo "Erro ao registrar conector Sink. HTTP Code: $HTTP_CODE"
  echo "$BODY"
fi

echo ""
echo "=== Conectores registrados ==="

# Listar conectores registrados
echo "Lista de conectores:"
curl -s "$KAFKA_CONNECT_URL/connectors"

echo ""
echo ""
echo "=== Status dos conectores ==="
echo "Status do Source (PostgreSQL):"
curl -s "$KAFKA_CONNECT_URL/connectors/postgres-message-source/status" | head -20

echo ""
echo "Status do Sink (MySQL):"
curl -s "$KAFKA_CONNECT_URL/connectors/mysql-message-sink/status" | head -20

echo ""
echo ""
echo "Script concluído!"

