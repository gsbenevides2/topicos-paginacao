#!/bin/bash

mkdir webappsTomcat -p
mkdir target -p

# Verifica se o container existe
if docker ps --format '{{.Names}}' | grep -q "Postgres"; then
    echo "Ambos os containers Tomcat e Postgres foram criados."
else
    echo "Um ou ambos os containers não foram criados.\nCriando-os"
    docker compose up -d postgres
fi

if ! command -v inotifywait >/dev/null 2>&1; then
    echo "O inotify-tools não está instalado."
    sudo apt-get update
    sudo apt-get install inotify-tools -y

fi


# Diretórios de origem e destino
while true; do
    files_changed=$(inotifywait -r -e modify,move,create,delete ./)
    mvn -B verify
done