# Fluxo de Status das Ordens de Serviço

## Visão Geral

O status da Ordem de Serviço (OS) representa o **"caminho de vida"** do serviço na oficina. Cada status é uma etapa bem definida do processo.

## Status Disponíveis

### 1. EM_ANALISE
**Estado Inicial** - Quando a OS é criada

- **O que acontece:**
  - O funcionário cria a OS com informações do problema
  - Realiza análise inicial do veículo
  - Define uma estimativa de valores
  
- **Dados permitidos:**
  - `descricaoProblema` - obrigatório
  - `valorMaoObra` - estimado (pode ser 0)
  - `valorPecas` - ainda desconhecido
  - `dataConclusao` - vazio (não deve ter conclusão ainda)

- **Ações possíveis:**
  - ✅ Editar valores (mão de obra, peças, descrição)
  - ✅ Ir para **EM_EXECUCAO**
  - ❌ Não pode ir direto para CONCLUIDO ou ENTREGUE

---

### 2. EM_EXECUCAO
**Em Andamento** - Serviço aprovado e em execução

- **Como chegar aqui:**
  - De EM_ANALISE → **Aprovar Serviço**
  - Significa: "Cliente aprovou o orçamento, começamos o trabalho"

- **O que acontece:**
  - Mecânico inicia o trabalho no veículo
  - Peças podem ser adquiridas e substituídas
  - Serviço é executado conforme descrito

- **Dados permitidos:**
  - `descricaoProblema` - pode ser atualizado
  - `valorMaoObra` - pode ser ajustado conforme trabalho avança
  - `valorPecas` - pode ser atualizado com peças reais
  - `dataConclusao` - ainda vazio

- **Ações possíveis:**
  - ✅ Editar valores conforme serviço avança
  - ✅ Ir para **CONCLUIDO**
  - ❌ Não pode voltar para EM_ANALISE
  - ❌ Não pode ir direto para ENTREGUE

---

### 3. CONCLUIDO
**Pronto na Oficina** - Serviço finalizado, aguardando entrega

- **Como chegar aqui:**
  - De EM_EXECUCAO → **Finalizar Serviço**
  - Significa: "O carro está pronto, vamos entregar ao cliente"

- **O que acontece automaticamente:**
  - `dataConclusao` é preenchida com data/hora atual
  - `valorTotal` é calculado = `valorMaoObra + valorPecas`
  - Status se torna CONCLUIDO

- **Dados permitidos:**
  - `descricaoProblema` - pode ser consultado
  - Valores - podem ser consultados mas com restrição (veja abaixo)
  - `dataConclusao` - preenchido automaticamente
  - `valorTotal` - calculado

- **Ações possíveis:**
  - ⚠️ Edição limitada (apenas se necessário ajuste)
  - ✅ Ir para **ENTREGUE**
  - ❌ Não pode voltar para EM_EXECUCAO
  - ❌ Idealmente não editar valores (serviço já terminou)

---

### 4. ENTREGUE
**Serviço Encerrado** - Cliente retirou o veículo

- **Como chegar aqui:**
  - De CONCLUIDO → **Entregar ao Cliente**
  - Significa: "Cliente pegou o carro, pagamento realizado, tudo encerrado"

- **O que acontece:**
  - Ordem fica no histórico
  - Não pode mais ser editada (somente consulta)
  - Usada em relatórios e histórico do cliente

- **Dados:**
  - Todos os dados congelados (apenas leitura)
  - Serve para análise e histórico

- **Ações possíveis:**
  - ❌ Nenhuma edição permitida
  - ✅ Apenas consultar dados
  - ✅ Aparece em relatórios históricos

---

## Fluxo Completo (Exemplo)

```
Ordem criada
      ↓
   EM_ANALISE
      ├─ Edita valores iniciais
      ├─ Consulta problema
      └─ Aprova para execução
      ↓
   EM_EXECUCAO
      ├─ Executa o trabalho
      ├─ Atualiza peças usadas
      ├─ Ajusta tempo de trabalho
      └─ Finaliza quando pronto
      ↓
   CONCLUIDO
      ├─ dataConclusao = hoje
      ├─ valorTotal = mão de obra + peças
      └─ Aguarda cliente buscar
      ↓
   ENTREGUE
      └─ Histórico (somente leitura)
```

---

## Implementação no Código

### OficinaService.java (Orquestração de Negócio)

```java
// Aprovar ordem (EM_ANALISE -> EM_EXECUCAO)
public boolean aprovarOrdemServico(int idOrdem)

// Finalizar ordem (EM_EXECUCAO -> CONCLUIDO)
public boolean finalizarOrdemServico(int idOrdem)

// Entregar ordem (CONCLUIDO -> ENTREGUE)
public boolean entregarOrdemServico(int idOrdem)
```

Cada método:
- ✅ Valida o status atual
- ✅ Impede transições inválidas
- ✅ Executa ações automáticas (ex: dataConclusao)
- ✅ Atualiza no DAO

### OrdemServicoController.java (Mediação)

```java
public boolean aprovarOrdemServico(int idOrdem)
public boolean finalizarOrdemServico(int idOrdem)
public boolean entregarOrdemServico(int idOrdem)
```

Chama o Service e trata exceções.

### OrdemServicoView.java (Interface)

Menu "5. Gerenciar Status (Fluxo de Vida)" oferece:

- **Se EM_ANALISE:**
  - Opção 1: Aprovar Serviço

- **Se EM_EXECUCAO:**
  - Opção 1: Finalizar Serviço

- **Se CONCLUIDO:**
  - Opção 1: Entregar ao Cliente

- **Se ENTREGUE:**
  - Apenas leitura, sem ações

---

## Validações Implementadas

### Validações Obrigatórias

1. **Transições de status respeitam ordem:**
   ```
   EM_ANALISE → EM_EXECUCAO → CONCLUIDO → ENTREGUE
   ```

2. **Não permite pular etapas:**
   - ❌ EM_ANALISE → ENTREGUE (direto)
   - ❌ EM_EXECUCAO → ENTREGUE (sem CONCLUIDO)

3. **Não permite retroceder:**
   - ❌ CONCLUIDO → EM_EXECUCAO
   - ❌ ENTREGUE → qualquer anterior

4. **Proteção de dados ENTREGUE:**
   - OS com status ENTREGUE não pode ser editada
   - Apenas consulta é permitida

### Validações Automáticas

- ✅ `dataConclusao` preenchida ao finalizar
- ✅ `valorTotal` recalculado ao finalizar
- ✅ Status atualizado no DAO

---

## Como Usar

### 1. Criar Ordem (Status = EM_ANALISE)
```
Menu Principal → Criar Ordem de Serviço
→ Preenche dados
→ Status automaticamente: EM_ANALISE
```

### 2. Aprovar e Começar Trabalho
```
Menu OS → Opção 5 (Gerenciar Status)
→ Digite ID da ordem
→ Vê: "Status Atual: EM_ANALISE"
→ Escolhe: Opção 1 (Aprovar)
→ Mensagem: "✓ Ordem aprovada! Status: EM_EXECUCAO"
```

### 3. Finalizar Serviço
```
Menu OS → Opção 5 (Gerenciar Status)
→ Digite ID da ordem
→ Vê: "Status Atual: EM_EXECUCAO"
→ Escolhe: Opção 1 (Finalizar)
→ Mensagem: "✓ Serviço finalizado! Status: CONCLUIDO"
→ Sistema preenche: dataConclusao, recalcula valorTotal
```

### 4. Entregar ao Cliente
```
Menu OS → Opção 5 (Gerenciar Status)
→ Digite ID da ordem
→ Vê: "Status Atual: CONCLUIDO"
→ Escolhe: Opção 1 (Entregar)
→ Mensagem: "✓ Ordem entregue! Status: ENTREGUE"
→ Nota: "Ordem não poderá mais ser editada"
```

### 5. Consultar Histórico
```
Menu OS → Opção 5 (Gerenciar Status)
→ Digite ID (com status ENTREGUE)
→ Vê: "❌ Ordem já foi entregue"
→ Pode consultar dados para histórico
```

---

## Mensagens de Erro

Se tentar transição inválida:

```
✗ ERRO: Ordem #5 está em status CONCLUIDO. 
Apenas ordens em EM_EXECUCAO podem ser finalizadas.
```

Se tentar editar ordem ENTREGUE:

```
✗ ERRO: Ordem #5 já foi entregue e não pode ser editada!
```

---

## Fluxo de Dados

```
OrdemServicoView (UI)
        ↓
   (usuário escolhe ação)
        ↓
OrdemServicoController (mediação)
        ↓
   (chama service)
        ↓
OficinaService (validações de negócio)
        ↓
   (chama DAO)
        ↓
OrdemServicoDAO (persistência)
        ↓
   (salva arquivo .dat)
        ↓
Ordem atualizada no sistema
```

---

## Regras de Negócio Resumidas

| Ação | De | Para | Automático |
|------|-----|------|-----------|
| Criar | - | EM_ANALISE | ✓ |
| Aprovar | EM_ANALISE | EM_EXECUCAO | - |
| Finalizar | EM_EXECUCAO | CONCLUIDO | ✓ dataConclusao, valorTotal |
| Entregar | CONCLUIDO | ENTREGUE | - |

---

## Próximos Passos (Futuros)

- [ ] Permitir reabrir OS com status especial (ex: Problema não resolvido)
- [ ] Adicionar campo `dataReabertura`
- [ ] Gerar relatórios por status (quantas ordens em cada etapa)
- [ ] Notificações quando OS aguarda cliente por muito tempo
- [ ] Cancelamento de OS (novo status CANCELADO)

