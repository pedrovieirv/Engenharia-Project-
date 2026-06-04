#!/bin/bash

# Script para compilar e executar o Sistema de Gerenciamento de Oficina

PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SRC_DIR="$PROJECT_DIR/src"
BIN_DIR="$PROJECT_DIR/bin"

# Criar diretório bin se não existir
mkdir -p "$BIN_DIR"

echo "=========================================="
echo "Sistema de Gerenciamento de Oficina"
echo "=========================================="
echo ""

# Compilar
echo "[1/2] Compilando arquivos Java..."
echo ""

javac -d "$BIN_DIR" \
    "$SRC_DIR/usuario/Usuario.java" \
    "$SRC_DIR/usuario/UsuarioController.java" \
    "$SRC_DIR/usuario/UsuarioView.java" \
    "$SRC_DIR/cliente/Cliente.java" \
    "$SRC_DIR/cliente/ClienteController.java" \
    "$SRC_DIR/cliente/ClienteView.java" \
    "$SRC_DIR/veiculo/Veiculo.java" \
    "$SRC_DIR/veiculo/VeiculoController.java" \
    "$SRC_DIR/veiculo/VeiculoView.java" \
    "$SRC_DIR/funcionario/Funcionario.java" \
    "$SRC_DIR/funcionario/FuncionarioController.java" \
    "$SRC_DIR/funcionario/FuncionarioView.java" \
    "$SRC_DIR/ordemServico/StatusOrdemServico.java" \
    "$SRC_DIR/ordemServico/OrdemServico.java" \
    "$SRC_DIR/ordemServico/OrdemServicoController.java" \
    "$SRC_DIR/ordemServico/OrdemServicoView.java" \
    "$SRC_DIR/main/MainSystem.java"

if [ $? -eq 0 ]; then
    echo "✓ Compilação concluída com sucesso!"
    echo ""
    echo "[2/2] Executando o programa..."
    echo ""
    cd "$BIN_DIR"
    java MainSystem
else
    echo "✗ Erro na compilação!"
    exit 1
fi
