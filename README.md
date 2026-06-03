# 🚀 Estudo Prático de Computação Concorrente

Este repositório foi desenvolvido para centralizar os laboratórios e tarefas práticos da disciplina de Computação Concorrente. O objetivo principal é explorar o uso de **Multithreading** em Java para otimizar o processamento de dados e resolver problemas clássicos de sincronização e concorrência através do paralelismo.

---

## 📌 Tarefa 01-01: Processamento Multithread com Vetores em Java

O objetivo desta primeira tarefa foi otimizar a inicialização e a análise de um vetor de grande escala de forma paralela.

### 📋 Requisitos da Tarefa
1. Alocar um vetor do tipo `double` com 200 milhões de entradas.
2. Inicializar o vetor com valores aleatórios usando múltiplas threads de forma paralela.
3. Exibir a mensagem `"Encerrou inicializacao"` imediatamente após o preenchimento total do vetor.
4. Computar e exibir a quantidade de valores no vetor que estão no intervalo aberto entre `0.25` e `0.75` (0.25 < valor < 0.75).

### 🛠️ Arquitetura e Solução
Para evitar condições de corrida (*Race Conditions*), o tamanho total do vetor (200.000.000) foi dividido igualmente entre as threads:
* **Thread 1 (t1):** Responsável por preencher o intervalo de índices de `0` até `100.000.000`.
* **Thread 2 (t2):** Responsável por preencher o intervalo de índices de `100.000.000` até `200.000.000`.
* **Sincronização:** A thread principal (`main`) utiliza o método `.join()` para aguardar o término da inicialização paralela antes de exibir a mensagem de conclusão e iniciar a leitura dos dados para a contagem.

---

## 📌 Tarefa 01-02: Leitura de Arquivos Multithread (Contador de Caracteres)

O objetivo desta segunda tarefa é otimizar o processamento de I/O (leitura de disco) e a manipulação de strings lendo milhares de arquivos de texto simultaneamente.

### 📋 Requisitos da Tarefa
1. Desenvolver um programa que leia todos os arquivos no formato `.txt` de um determinado diretório.
2. Realizar a contagem consolidada das letras do alfabeto ('A' a 'Z') encontradas em todos os arquivos combinados.
3. Exibir na tela o tempo total de execução gasto para realizar todo o processamento.
4. Testar o programa inicialmente com um diretório de amostra (100 arquivos) e, em seguida, com o diretório de produção (35.000 arquivos).
5. Efetuar análises de desempenho variando a quantidade de threads para submeter o código com a melhor performance para a arquitetura do seu processador.

### 🛠️ Estrutura de Otimização
* **Divisão de Arquivos:** A lista total de arquivos do diretório é dividida em fatias iguais para cada thread criada.
* **Mapeamento Direto (ASCII):** A contagem utiliza a diferença inteira de bytes baseada no caractere `'A'` para incrementar diretamente um índice de array de 26 posições, dispensando estruturas condicionais lentas como `switch` ou `if/else`.
* **Evitando Concorrência:** Cada thread gerencia seu próprio array local de contagem. Após todas as threads terminarem a leitura via `.join()`, a thread principal junta os totais, evitando gargalos de bloqueio durante o processamento de I/O.

---

## 📌 Tarefa 01-03: Sincronização de Threads (Sistema Bancário Concorrente)

O objetivo desta terceira tarefa é simular e resolver o problema de concorrência e consistência de dados em memória compartilhada utilizando travas de sincronização.

### 📋 Requisitos da Tarefa
1. Implementar uma classe chamada `Conta` com um atributo `double saldo` (ou valor) e três métodos fundamentais:
   * **Deposito:** Recebe um valor e o soma ao saldo.
   * **Saque:** Recebe um valor e o subtrai do saldo (apenas se houver saldo disponível).
   * **Saldo:** Retorna o saldo atual.
2. Implementar uma Thread denominada `Deposito` que efetua depósitos de valores aleatórios a cada 0,1 segundos.
3. Implementar uma Thread denominada `Saque` que efetua saques aleatórios a cada 0,1 segundos.
4. Executar **três threads de saques** e **duas threads de depósitos** simultaneamente, exibindo o saldo atualizado no console a cada operação realizada.

### 🛠️ Estrutura de Sincronização
* **Exclusão Mútua:** Para evitar que o saldo seja corrompido quando múltiplas linhas de execução tentarem ler e gravar na mesma conta ao mesmo tempo, os métodos de modificação do saldo devem ser sincronizados (`synchronized`), garantindo consistência atômica às operações.

---

## 🚀 Como Executar as Tarefas

### Pré-requisitos
* Java JDK 11 ou superior instalado.
* Git configurado.

### Executando a Tarefa 01-01
```bash
cd Tarefa_01_01
javac src/Main.java -d bin
java -cp bin Main
