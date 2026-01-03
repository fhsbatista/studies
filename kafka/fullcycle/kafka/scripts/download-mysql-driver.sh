#!/bin/bash

# Script para baixar o driver MySQL manualmente
# Execute este script antes de iniciar o docker-compose

DRIVER_DIR="./drivers"
DRIVER_FILE="mysql-connector-j-8.2.0.jar"
DRIVER_URL="https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.2.0/mysql-connector-j-8.2.0.jar"

echo "Criando diretório de drivers..."
mkdir -p "$DRIVER_DIR"

echo "Baixando driver MySQL..."
if curl -L -f -o "$DRIVER_DIR/$DRIVER_FILE" "$DRIVER_URL"; then
  echo "Driver MySQL baixado com sucesso: $DRIVER_DIR/$DRIVER_FILE"
  ls -lh "$DRIVER_DIR/$DRIVER_FILE"
else
  echo "Erro ao baixar o driver MySQL"
  echo "Tentando URL alternativa..."
  
  # Tentar versão antiga (mysql-connector-java)
  DRIVER_FILE_OLD="mysql-connector-java-8.0.33.jar"
  DRIVER_URL_OLD="https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar"
  
  if curl -L -f -o "$DRIVER_DIR/$DRIVER_FILE_OLD" "$DRIVER_URL_OLD"; then
    echo "Driver MySQL (versão antiga) baixado: $DRIVER_DIR/$DRIVER_FILE_OLD"
  else
    echo "ERRO: Não foi possível baixar o driver MySQL"
    echo "Por favor, baixe manualmente de:"
    echo "  - https://dev.mysql.com/downloads/connector/j/"
    echo "  - Ou use: wget $DRIVER_URL"
    exit 1
  fi
fi

