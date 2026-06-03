# 🚀 Estudo Prático de Computação Concorrente

Este repositório foi desenvolvido para centralizar os laboratórios e tarefas práticos da disciplina de Computação Concorrente. O objetivo principal é explorar o uso de **Multithreading** em Java para otimizar o processamento de dados e resolver problemas clássicos de sincronização e concorrência através do paralelismo.

---

## 💻 Tecnologias e Conceitos Praticados

* **Java Threads:** Criação e gerenciamento de linhas de execução paralelas (`extends Thread` / `Runnable`).
* **Exclusão Mútua:** Bloqueios de seção crítica utilizando o modificador `synchronized`.
* **Sinalização Inter-threads:** Coordenação de fluxo ativa através de `wait()`, `notify()` e `notifyAll()`.
* **Controle de I/O e Escalonamento:** Divisão síncrona de carga de trabalho e monitoramento de desempenho.

---

## 📌 Tarefa 01-01: Processamento Multithread com Vetores

O objetivo desta primeira tarefa foi otimizar a inicialização e a análise de um vetor de grande escala de forma paralela.

### 📋 Requisitos da Tarefa
1. Alocar um vetor do tipo `double` com **200 milhões de entradas**.
2. Inicializar o vetor com valores aleatórios usando múltiplas threads de forma paralela.
3. Exibir a mensagem `"Encerrou inicializacao"` imediatamente após o preenchimento total do vetor.
4. Computar e exibir a quantidade de valores no vetor que estão no intervalo aberto entre `0.25` e `0.75` ($0.25 < valor < 0.75$).

### 🛠️ Arquitetura e Solução
Para evitar condições de corrida (*Race Conditions*), o tamanho total do vetor ($200.000.000$) foi dividido igualmente entre as threads:

| Thread | Intervalo de Índices (Escopo) |
| :--- | :--- |
| **Thread 1 (`t1`)** | De `0` até `100.000.000` |
| **Thread 2 (`t2`)** | De `100.000.000` até `200.000.000` |

> 🔒 **Sincronização:** A thread principal (`main`) utiliza o método `.join()` para aguardar o término da inicialização paralela antes de exibir a mensagem de conclusão e iniciar a leitura dos dados para a contagem.

---

## 📌 Tarefa 01-02: Leitura de Arquivos (Contador de Caracteres)

O objetivo desta segunda tarefa é otimizar o processamento de I/O (leitura de disco) e a manipulação de strings lendo milhares de arquivos de texto simultaneamente.

### 📋 Requisitos da Tarefa
1. Desenvolver um programa que leia todos os arquivos no formato `.txt` de um determinado diretório.
2. Realizar a contagem consolidada das letras do alfabeto (`'A'` a `'Z'`) encontradas em todos os arquivos combinados.
3. Exibir na tela o tempo total de execução gasto para realizar todo o processamento.
4. Testar o programa com a seguinte volumetria:
   * **Ambiente de Amostra:** 100 arquivos.
   * **Ambiente de Produção:** 35.000 arquivos.
5. Efetuar análises de desempenho variando a quantidade de threads para validar a melhor performance na arquitetura do processador.

### 🛠️ Estrutura de Otimização
* **Divisão de Arquivos:** A lista total de arquivos do diretório é dividida em fatias iguais para cada thread criada.
* **Mapeamento Direto (ASCII):** A contagem utiliza a diferença inteira de bytes baseada no caractere `'A'` para incrementar diretamente um índice de array de 26 posições, dispensando estruturas condicionais lentas como `switch` ou `if/else`.
* **Lock-Free no Processamento:** Cada thread gerencia seu próprio array local de contagem. Após todas as threads terminarem a leitura via `.join()`, a thread principal junta os totais, evitando gargalos de bloqueio concorrente durante o processamento de I/O.

---

## 📌 Tarefa 01-03: Sincronização de Threads (Sistema Bancário)

O objetivo desta terceira tarefa é simular e resolver o problema de concorrência e consistência de dados em memória compartilhada utilizando travas de sincronização e sinalização de estados.

### 📋 Requisitos da Tarefa
1. Implementar uma classe chamada `Conta` com um atributo `double saldo` e três métodos fundamentais:
   * **Deposito:** Recebe um valor, soma ao saldo e notifica as threads em espera.
   * **Saque:** Recebe um valor e o subtrai do saldo (apenas se houver saldo suficiente disponível).
   * **Saldo:** Retorna o saldo atual.
2. Implementar uma Thread denominada `Saque` que efetua saques aleatórios a cada 0,1 segundos.
3. Implementar uma Thread denominada `Deposito` que efetua depósitos de valores aleatórios a cada 0,1 segundos.
4. Executar **três threads de saques** e **duas threads de depósitos** simultaneamente, exibindo o saldo formatado no console a cada operação.

### 🛠️ Estrutura de Sincronização e Sinalização
* **Exclusão Mútua:** Os métodos de alteração de saldo utilizam blocos `synchronized(this)`, garantindo atomicidade às operações (uma única thread manipula o saldo por vez).
* **Sinalização Inter-threads (`wait` / `notifyAll`):** Para evitar o desperdício de ciclos de processamento da CPU (*busy wait*), a thread de saque entra em estado de espera ativa utilizando o método `this.wait()` caso o saldo seja insuficiente. Assim que qualquer thread de depósito injeta fundos na conta, ela dispara o `this.notifyAll()`, acordando as threads de saque.
* **Formatação Decimais:** Exibição das saídas textuais padronizadas em duas casas decimais utilizando `String.format("%.2f", valor)`.

---

## 📌 TDE: Simulador de Biblioteca Concorrente

Implementação de um cenário de empréstimo distribuído de recursos finitos, aplicando restrições rígidas de sincronia de processos para evitar condições de corrida (*race conditions*).

### 📋 Requisitos do TDE
1. Biblioteca composta por **10 livros compartilhados** e **3 usuários concorrentes**.
2. **Regras de Negócio:**
   * Cada livro pode estar emprestado a apenas um usuário por vez.
   * Cada usuário (Thread) só pode pegar um livro por vez.
   * O usuário escolhe um livro aleatoriamente (de 1 a 10). Caso o livro esteja ocupado, a thread deve pausar (`wait()`) e aguardar até que o recurso específico seja devolvido.
   * Ao devolver o livro, o usuário deve liberar o recurso e sinalizar (`notifyAll()`) as outras threads concorrentes.
   * Uso de intervalos aleatórios (de 1 a 2 segundos) via `Thread.sleep()` para simular o tempo de retenção do livro e o tempo de descanso do usuário antes de tentar um novo empréstimo.

### 🛠️ Arquitetura e Saída do Programa
* **Controle de Estado:** Gerenciamento dos livros por meio de monitoramento atômico de disponibilidade de posições (Array booleano de estados).
* **Monitoramento por Console:** O programa exibe em tempo real cada mudança de estado da seção crítica utilizando a formatação padronizada:

```text
Usuário 1 – Emprestou livro 2
Usuário 2 – Emprestou livro 3
Usuário 3 – Esperando livro 2 ficar disponível
Usuário 1 – Devolveu livro 2
Usuário 3 – Emprestou livro 2
Usuário 2 – Devolveu livro 3
...
