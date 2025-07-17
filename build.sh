#!/bin/bash

# Script para construir as imagens Docker e carregá-las no Minikube.

echo "✅ Apontando o terminal para o Docker do Minikube..."
eval $(minikube -p minikube docker-env)

echo "🚀 Construindo a imagem do backend (Spring)..."
# O nome da imagem será 'clinica-backend:latest'
docker build -t clinica-backend:latest ./clinica

echo "🚀 Construindo a imagem do frontend (React)..."
# O nome da imagem será 'clinica-frontend:latest'
docker build -t clinica-frontend:latest ./frontend

echo "🎉 Build concluído! Imagens carregadas no Minikube."
echo "Use 'minikube image ls' para verificar."

# Para voltar ao ambiente Docker normal do seu PC (opcional)
# eval $(minikube docker-env -u)