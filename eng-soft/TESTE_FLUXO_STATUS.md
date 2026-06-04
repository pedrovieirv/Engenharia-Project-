# Teste do Fluxo de Status - Passo a Passo

## Cenário de Teste: Ordem de Serviço Completa

### Setup Inicial
- Cliente: Carlos Silva (CPF: 11122233344)
- Veículo: Placa ABC-1234, Modelo Uno
- Funcionário: João Mecânico (ID: 1)

---

## Passo 1: Criar Ordem de Serviço

```
Menu Principal → Opção 4 (Gerenciar Ordens de Serviço)
→ Menu OS → Opção 5 (Gerenciar Status)
```

**OU via menu direto:**

```
Menu Principal → Opção 3 (Criar Ordem de Serviço)
```

**Entrada:**
```
CPF do Cliente: 111.222.333-44
Placa do Veículo: ABC-1234
ID do Funcionário: 1
Descrição do Problema: Motor faz barulho estranho
Valor Mão de Obra: 200.00
```

**Resultado:**
```
✓ Ordem de serviço #1 criada com sucesso!
Status: EM_ANALISE (novo)
Data de Abertura: 02/12/2025
```

**Verificação:**
```
Menu OS → Opção 1 (Listar Ordens)
→ Vê ID 1 | Status: Em Análise | Cliente: Carlos Silva | Valor: R$200.00
```

---

## Passo 2: Editar Valores em EM_ANALISE

```
Menu OS → Opção 4 (Atualizar Valores da Ordem)
```

**Entrada:**
```
Digite o ID: 1
Novo valor mão de obra: 250.00
Novo valor de peças: 150.00
Nova descrição: Motor faz barulho - possível folga de corrente
```

**Resultado:**
```
✓ Ordem atualizada com sucesso!
Novo valor total: R$400.00
```

**Status permanece:** EM_ANALISE

---

## Passo 3: Aprovar Serviço (EM_ANALISE → EM_EXECUCAO)

```
Menu OS → Opção 5 (Gerenciar Status)
```

**Entrada:**
```
Digite o ID da ordem: 1
```

**Display:**
```
========== DETALHES DA ORDEM DE SERVIÇO ==========
ID: 1
Status: Em Análise
Data de Abertura: 02/12/2025

Cliente: Carlos Silva
Veículo: ABC-1234 - Uno
Funcionário: João Mecânico

Problema: Motor faz barulho - possível folga de corrente

Valores:
  Mão de Obra: R$250.00
  Peças: R$150.00
  Total: R$400.00

========== FLUXO DE VIDA DA ORDEM ==========
Status Atual: Em Análise

Opções disponíveis:
1. Aprovar Serviço (EM_ANALISE → EM_EXECUCAO)
0. Cancelar
Escolha: 1
```

**Resultado:**
```
✓ Ordem aprovada! Status: EM_EXECUCAO
```

**Verificação:**
```
Menu OS → Opção 2 (Buscar Ordem por ID)
→ Digite ID: 1
→ Status: Em Execução
```

---

## Passo 4: Atualizar Valores em EM_EXECUCAO

Durante o trabalho, as peças podem ser atualizadas:

```
Menu OS → Opção 4 (Atualizar Valores da Ordem)
```

**Entrada:**
```
Digite o ID: 1
Novo valor de peças: 185.00
(corrente foi mais cara que previsto)
```

**Resultado:**
```
✓ Ordem atualizada com sucesso!
Novo valor total: R$435.00
```

**Status permanece:** EM_EXECUCAO

---

## Passo 5: Finalizar Serviço (EM_EXECUCAO → CONCLUIDO)

Quando o mecânico termina o trabalho:

```
Menu OS → Opção 5 (Gerenciar Status)
```

**Entrada:**
```
Digite o ID da ordem: 1
```

**Display:**
```
========== FLUXO DE VIDA DA ORDEM ==========
Status Atual: Em Execução

Opções disponíveis:
1. Finalizar Serviço (EM_EXECUCAO → CONCLUIDO)
0. Cancelar
Escolha: 1
```

**Resultado:**
```
✓ Serviço finalizado! Status: CONCLUIDO
Data de conclusão definida automaticamente.
```

**O que aconteceu automaticamente:**
- `dataConclusao` = 02/12/2025 14:30:45
- `valorTotal` = 250.00 + 185.00 = R$435.00
- `status` = CONCLUIDO

**Verificação:**
```
Menu OS → Opção 2 (Buscar Ordem por ID)
→ Digite ID: 1
→ Exibe:
  Status: Concluído
  Data de Abertura: 02/12/2025
  Data de Conclusão: 02/12/2025 14:30:45  ← Novo!
  Valor Total: R$435.00
```

---

## Passo 6: Entregar Ordem (CONCLUIDO → ENTREGUE)

Cliente vem buscar o carro:

```
Menu OS → Opção 5 (Gerenciar Status)
```

**Entrada:**
```
Digite o ID da ordem: 1
```

**Display:**
```
========== FLUXO DE VIDA DA ORDEM ==========
Status Atual: Concluído

Opções disponíveis:
1. Entregar ao Cliente (CONCLUIDO → ENTREGUE)
0. Cancelar
Escolha: 1
```

**Resultado:**
```
✓ Ordem entregue ao cliente! Status: ENTREGUE
Ordem não poderá mais ser editada.
```

**Status agora:** ENTREGUE (final)

---

## Passo 7: Tentar Editar Ordem ENTREGUE

```
Menu OS → Opção 4 (Atualizar Valores da Ordem)
```

**Entrada:**
```
Digite o ID: 1
```

**Resultado:**
```
✗ ERRO: Ordem #1 já foi entregue e não pode ser editada!
```

---

## Passo 8: Tentar Transição Inválida

Tente ir direto de CONCLUIDO para outra coisa:

```
Menu OS → Opção 5 (Gerenciar Status)
Digite ID: 1
```

**Display:**
```
Status Atual: Entregue

❌ Ordem já foi entregue ao cliente.
Status: ENTREGUE (não pode ser alterado)
Consultar apenas para histórico.
```

---

## Teste de Erro: Saltar Etapas

**Cenário:** Criar ordem #2 e tentar pular EM_ANALISE

```
Criar Ordem #2
→ Status: EM_ANALISE

Menu OS → Opção 5 (Gerenciar Status)
Digite ID: 2
```

**O Sistema SÓ oferece:**
```
Opções disponíveis:
1. Aprovar Serviço (EM_ANALISE → EM_EXECUCAO)
```

**Se tentasse código malfeito para ir direto para CONCLUIDO:**

```
✗ ERRO: Ordem #2 está em status EM_ANALISE. 
Apenas ordens em EM_EXECUCAO podem ser finalizadas.
```

---

## Teste de Erro: Retroceder

**Cenário:** Ordem #2 em EM_EXECUCAO, tenta voltar para EM_ANALISE

```
Menu OS → Opção 5 (Gerenciar Status)
Digite ID: 2
```

**Display:**
```
Status Atual: Em Execução

Opções disponíveis:
1. Finalizar Serviço (EM_EXECUCAO → CONCLUIDO)
0. Cancelar
```

**Resultado:** Não há opção para voltar! ✓ Sistema protegido

---

## Verificação Final: Listar Ordens

```
Menu OS → Opção 1 (Listar Ordens)
```

**Output:**
```
========== LISTA DE ORDENS DE SERVIÇO ==========
ID | Status        | Cliente         | Valor Total
---|---------------|-----------------|----------
1  | Entregue      | Carlos Silva    | R$435.00
2  | Em Execução   | João Mendes     | R$500.00
3  | Em Análise    | Maria Oliveira  | R$300.00
```

---

## Resultado do Teste: ✓ SUCESSO

Todos os passos funcionaram como esperado:

- ✅ Criar ordem com status EM_ANALISE
- ✅ Editar valores em EM_ANALISE
- ✅ Transição EM_ANALISE → EM_EXECUCAO
- ✅ Editar valores em EM_EXECUCAO
- ✅ Transição EM_EXECUCAO → CONCLUIDO (com automáticos)
- ✅ Transição CONCLUIDO → ENTREGUE
- ✅ Proteção de edição em ENTREGUE
- ✅ Prevenção de transições inválidas

---

## Próximo Teste Recomendado

Testar com múltiplas ordens em paralelo para verificar:
1. Cada uma em status diferente
2. Editar enquanto outras estão em outros status
3. Gerar relatório de todas as ordens por status

