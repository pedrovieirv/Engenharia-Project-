# Como Compilar e Executar o Sistema de Gerenciamento de Oficina

## Pré-requisitos

- **Java JDK** (versão 8 ou superior) instalado no seu computador
- Terminal/Prompt de comando

## Verificar se Java está instalado

Execute no terminal:

```bash
java -version
javac -version
```

Se aparecer uma mensagem com a versão do Java, está tudo certo!

---

## Opção 1: Usando o Script (Recomendado)

### Linux/Mac

1. Abra o terminal na pasta do projeto
2. Dê permissão de execução ao script:
   ```bash
   chmod +x executar.sh
   ```
3. Execute:
   ```bash
   ./executar.sh
   ```

### Windows

1. Abra o Command Prompt (cmd) na pasta do projeto
2. Execute:
   ```bash
   executar.bat
   ```

---

## Opção 2: Compilar Manualmente

### Passo 1: Criar o diretório de saída

```bash
mkdir -p bin
```

### Passo 2: Compilar os arquivos

Na pasta do projeto, execute (em uma linha):

```bash
javac -d bin src/usuario/Usuario.java src/usuario/UsuarioController.java src/usuario/UsuarioView.java src/cliente/Cliente.java src/cliente/ClienteController.java src/cliente/ClienteView.java src/veiculo/Veiculo.java src/veiculo/VeiculoController.java src/veiculo/VeiculoView.java src/funcionario/Funcionario.java src/funcionario/FuncionarioController.java src/funcionario/FuncionarioView.java src/ordemServico/StatusOrdemServico.java src/ordemServico/OrdemServico.java src/ordemServico/OrdemServicoController.java src/ordemServico/OrdemServicoView.java src/main/MainSystem.java
```

### Passo 3: Executar o programa

```bash
cd bin
java MainSystem
```

---

## Opção 3: Compilar Passo a Passo (Melhor para debug)

Se receber erros de compilação, compile cada módulo separadamente:

```bash
# Crie o diretório bin
mkdir -p bin

# Compile na ordem de dependência
javac -d bin src/usuario/Usuario.java
javac -d bin src/usuario/UsuarioController.java
javac -d bin src/usuario/UsuarioView.java
javac -d bin src/cliente/Cliente.java
javac -d bin src/cliente/ClienteController.java
javac -d bin src/cliente/ClienteView.java
javac -d bin src/veiculo/Veiculo.java
javac -d bin src/veiculo/VeiculoController.java
javac -d bin src/veiculo/VeiculoView.java
javac -d bin src/funcionario/Funcionario.java
javac -d bin src/funcionario/FuncionarioController.java
javac -d bin src/funcionario/FuncionarioView.java
javac -d bin src/ordemServico/StatusOrdemServico.java
javac -d bin src/ordemServico/OrdemServico.java
javac -d bin src/ordemServico/OrdemServicoController.java
javac -d bin src/ordemServico/OrdemServicoView.java
javac -d bin src/main/MainSystem.java

# Execute
cd bin
java MainSystem
```

---

## Usando o Programa

Ao iniciar o programa, você verá um menu de login.

### Credenciais Padrão (já cadastradas):

**Gerente:**
- Email: `carlos@oficina.com`
- Senha: `senha123`

**Funcionário 1:**
- Email: `joao@oficina.com`
- Senha: `senha456`

**Funcionário 2:**
- Email: `maria@oficina.com`
- Senha: `senha789`

### Funcionalidades Disponíveis:

- **Gerenciar Clientes**: Criar, listar, buscar, atualizar e deletar clientes
- **Gerenciar Veículos**: Criar, listar, buscar e atualizar veículos
- **Gerenciar Funcionários**: Criar, listar e atualizar funcionários (apenas gerente)
- **Gerenciar Ordens de Serviço**: Criar, listar, atualizar status e valores

---

## Estrutura de Pastas

```
gerenciamento-de-oficina/
├── src/                          # Código-fonte
│   ├── cliente/                  # Módulo de clientes
│   ├── veiculo/                  # Módulo de veículos
│   ├── funcionario/              # Módulo de funcionários
│   ├── ordemServico/             # Módulo de ordens de serviço
│   ├── usuario/                  # Módulo de usuários
│   └── main/                     # Classe principal (MainSystem)
├── bin/                          # Arquivos compilados (criado automaticamente)
├── executar.sh                   # Script de execução (Linux/Mac)
├── executar.bat                  # Script de execução (Windows)
└── README.md                     # Documentação do projeto
```

---

## Solução de Problemas

### Erro: "javac: command not found"
- Java não está instalado ou não está no PATH
- Instale o JDK apropriado para seu sistema operacional

### Erro: "Class not found"
- Certifique-se de compilar todas as classes antes
- Verifique se está executando do diretório `bin`

### Erro de arquivo/diretório
- Verifique se está na pasta raiz do projeto
- Certifique-se de que o arquivo existe no caminho especificado

---

## Limpando Arquivos Compilados

Para remover os arquivos compilados e começar do zero:

**Linux/Mac:**
```bash
rm -rf bin
```

**Windows (cmd):**
```bash
rmdir /s /q bin
```

---

Divirta-se com o Sistema de Gerenciamento de Oficina! 🛠️
