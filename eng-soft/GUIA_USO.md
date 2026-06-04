# 📖 Guia de Uso - Sistema de Gerenciamento de Oficina

## 🚀 Como Compilar e Executar

### 1️⃣ Compilar o Projeto

```bash
cd /home/bzer4/Documentos/atividades/teste2/gerenciamento-de-oficina
javac -d bin -cp src src/**/*.java
```

**Se compilar com sucesso**, nenhuma mensagem será exibida.

---

### 2️⃣ Executar o Sistema

```bash
java -cp bin MainSystem
```

O sistema exibirá:
```
╔════════════════════════════════════════════╗
║  Sistema de Gerenciamento de Oficina      ║
╚════════════════════════════════════════════╝

✓ Dados iniciais carregados com sucesso!

╔════════════════════════════════════════════╗
║           MENU DE LOGIN                    ║
╚════════════════════════════════════════════╝

1. Fazer Login
2. Sair

Escolha uma opção:
```

---

## 🔐 Credenciais de Teste

### Funcionários Disponíveis

| Email | Senha | Tipo | Nome |
|-------|-------|------|------|
| carlos@oficina.com | senha123 | Gerente | Carlos Silva |
| joao@oficina.com | senha456 | Operacional | João Santos |
| maria@oficina.com | senha789 | Operacional | Maria Oliveira |

**Recomendação**: Fazer login com `carlos@oficina.com` para teste do sistema completo.

---

## 📋 Fluxo Principal

### 1. Menu de Login
```
Escolha: 1
Digite seu email: carlos@oficina.com
Digite sua senha: senha123
✓ Login realizado com sucesso!
```

### 2. Menu Principal
Após login, você verá:
```
╔════════════════════════════════════════════╗
║           MENU PRINCIPAL                   ║
╚════════════════════════════════════════════╝

1. Gerenciar Clientes
2. Gerenciar Veículos
3. Gerenciar Funcionários
4. Gerenciar Ordens de Serviço
5. Fazer Logout
6. Sair

Escolha uma opção:
```

---

## 👥 Funcionalidades por Menu

### 1. Gerenciar Clientes
```
1. Criar novo cliente
2. Listar clientes
3. Buscar cliente por ID
4. Buscar cliente por CPF
5. Buscar cliente por Email
6. Atualizar cliente
7. Remover cliente
8. Voltar ao menu principal
```

**Exemplo - Criar Cliente**:
```
Escolha: 1
Digite nome: João Silva
Digite CPF: 12345678900
Digite telefone: 11-99999-8888
Digite endereço: Rua Principal, 100
Digite email: joao@email.com
✓ Cliente criado com sucesso!
```

### 2. Gerenciar Veículos
```
1. Criar novo veículo
2. Listar veículos
3. Buscar veículo por ID
4. Buscar veículo por Placa
5. Buscar veículos por Marca
6. Atualizar veículo
7. Remover veículo
8. Voltar ao menu principal
```

**Exemplo - Criar Veículo**:
```
Escolha: 1
Digite placa: XYZ-1234
Digite modelo: Corolla
Digite marca: Toyota
Digite ano: 2022
Digite cor: Prata
✓ Veículo criado com sucesso!
```

### 3. Gerenciar Funcionários
```
1. Criar novo funcionário
2. Listar funcionários
3. Listar gerentes
4. Listar operacionais
5. Buscar funcionário por ID
6. Atualizar funcionário
7. Remover funcionário
8. Voltar ao menu principal
```

**Exemplo - Criar Funcionário**:
```
Escolha: 1
Digite nome: Pedro Costa
Digite CPF: 98765432100
Digite email: pedro@oficina.com
Digite senha: senha999
Funcionário é gerente? (s/n): n
Digite data de admissão (dd/MM/yyyy): 01/01/2024
✓ Funcionário criado com sucesso!
```

### 4. Gerenciar Ordens de Serviço
```
1. Criar nova ordem de serviço
2. Listar ordens de serviço
3. Buscar ordem por ID
4. Atualizar ordem de serviço
5. Remover ordem de serviço
6. Listar ordens por cliente
7. Listar ordens por funcionário
8. Listar ordens por status
9. Voltar ao menu principal
```

**Exemplo - Criar Ordem de Serviço**:
```
Escolha: 1
Digite ID do cliente: 1
Digite ID do veículo: 1
Digite ID do funcionário: 1
Digite descrição do problema: Trocar óleo
Digite valor mão de obra: 50.00
✓ Ordem de serviço criada com sucesso!
```

---

## ⚙️ Dados Iniciais Carregados

### Clientes
- **Pedro Oliveira** (ID: 1) - CPF: 11122233344
- **Ana Costa** (ID: 2) - CPF: 22233344455

### Veículos
- **Volkswagen Fusca** (ID: 1) - Placa: ABC-1234 (2015)
- **Volkswagen Gol** (ID: 2) - Placa: DEF-5678 (2018)
- **Honda Civic** (ID: 3) - Placa: GHI-9012 (2020)

### Funcionários
- **Carlos Silva** - Gerente (carlos@oficina.com)
- **João Santos** - Operacional (joao@oficina.com)
- **Maria Oliveira** - Operacional (maria@oficina.com)

### Ordens de Serviço Iniciais
- **Ordem 1**: Pedro Oliveira + Fusca + Carlos Silva - "Trocar óleo e filtro"
- **Ordem 2**: Ana Costa + Gol + João Santos - "Revisar freios"

---

## 🛑 Saindo do Sistema

### Opção 1: Via Menu Principal
```
Escolha: 5
✓ Logout realizado com sucesso!
(Retorna ao menu de login)
```

### Opção 2: Sair Completamente
```
Escolha: 6
✓ Sistema encerrado. Até logo!
```

---

## 📝 Dicas de Uso

### ✅ Boas Práticas
1. **Sempre fazer login** antes de usar qualquer funcionalidade
2. **Verificar IDs** antes de buscar ou remover registros
3. **Use a função listar** para ver todos os IDs disponíveis
4. **Dados de teste** são carregados automaticamente na inicialização

### ⚠️ Limitações Conhecidas
1. **Dados em Memória**: Todos os dados são perdidos ao encerrar (sem persistência)
2. **Validação Mínima**: Não há validação de CPF/Email/Telefone
3. **IDs Manuais**: IDs devem ser fornecidos manualmente ao criar registros
4. **Sem Backup**: Não há sistema de backup automático

### 🔍 Teste do Sistema Completo

```bash
# Terminal 1: Compilar
cd /home/bzer4/Documentos/atividades/teste2/gerenciamento-de-oficina
javac -d bin -cp src src/**/*.java

# Terminal 2: Executar
java -cp bin MainSystem

# Interação
Escolha: 1              # Ir para login
joao@oficina.com        # Email de operacional
senha456                # Senha
Escolha: 1              # Gerenciar clientes
Escolha: 2              # Listar clientes
Escolha: 8              # Voltar
Escolha: 5              # Logout
Escolha: 2              # Sair
```

---

## 🐛 Solução de Problemas

### Erro: "Arquivo não encontrado"
```bash
# Verifique se está no diretório correto
pwd
# Deve mostrar: /home/bzer4/Documentos/atividades/teste2/gerenciamento-de-oficina
```

### Erro: "Classe não encontrada"
```bash
# Recompile o projeto
javac -d bin -cp src src/**/*.java
```

### Erro: "NoSuchElementException"
```bash
# Ocorre quando o Scanner tenta ler dados que não existem
# Solução: Forneça entrada válida ou use pipe com echo
echo -e "1\ncarlos@oficina.com\nsenha123\n5\n2" | java -cp bin MainSystem
```

### Menu não responde
```bash
# Se o programa ficar pendurado, pressione Ctrl+C para sair
Ctrl + C
```

---

## 📊 Estrutura de Dados

### Classes Principais
```
Usuario (abstrato)
  └─ Funcionario

Cliente
Veiculo
OrdemServico (referencia Cliente, Veiculo, Funcionario)
StatusOrdemServico (enum)
```

### Controllers
- ClienteController
- VeiculoController
- FuncionarioController
- UsuarioController
- OrdemServicoController

---

## 🎓 Aprendizado

Este sistema demonstra:
- ✅ Padrão MVC em Java puro
- ✅ Herança (Usuario → Funcionario)
- ✅ Encapsulamento (getters/setters)
- ✅ Coleções (ArrayList)
- ✅ POJO (Plain Old Java Objects)
- ✅ Scanner para entrada de dados
- ✅ Tratamento de erros básico

---

**Última Atualização**: 2024
**Versão**: 1.0
