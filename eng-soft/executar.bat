@echo off
REM Script para compilar e executar no Windows

setlocal enabledelayedexpansion

set PROJECT_DIR=%~dp0
set SRC_DIR=%PROJECT_DIR%src
set BIN_DIR=%PROJECT_DIR%bin

REM Criar diretório bin se não existir
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo ==========================================
echo Sistema de Gerenciamento de Oficina
echo ==========================================
echo.

echo [1/2] Compilando arquivos Java...
echo.

javac -d "%BIN_DIR%" ^
    "%SRC_DIR%\usuario\Usuario.java" ^
    "%SRC_DIR%\usuario\UsuarioController.java" ^
    "%SRC_DIR%\usuario\UsuarioView.java" ^
    "%SRC_DIR%\cliente\Cliente.java" ^
    "%SRC_DIR%\cliente\ClienteController.java" ^
    "%SRC_DIR%\cliente\ClienteView.java" ^
    "%SRC_DIR%\veiculo\Veiculo.java" ^
    "%SRC_DIR%\veiculo\VeiculoController.java" ^
    "%SRC_DIR%\veiculo\VeiculoView.java" ^
    "%SRC_DIR%\funcionario\Funcionario.java" ^
    "%SRC_DIR%\funcionario\FuncionarioController.java" ^
    "%SRC_DIR%\funcionario\FuncionarioView.java" ^
    "%SRC_DIR%\ordemServico\StatusOrdemServico.java" ^
    "%SRC_DIR%\ordemServico\OrdemServico.java" ^
    "%SRC_DIR%\ordemServico\OrdemServicoController.java" ^
    "%SRC_DIR%\ordemServico\OrdemServicoView.java" ^
    "%SRC_DIR%\main\MainSystem.java"

if %ERRORLEVEL% equ 0 (
    echo ✓ Compilação concluída com sucesso!
    echo.
    echo [2/2] Executando o programa...
    echo.
    cd /d "%BIN_DIR%"
    java MainSystem
) else (
    echo ✗ Erro na compilação!
    exit /b 1
)

endlocal
