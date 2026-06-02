# 🚀 Estudo Prático de Computação Concorrente

Este repositório foi desenvolvido para centralizar os laboratórios e tarefas práticos da disciplina de Computação Concorrente. O objetivo principal é explorar o uso de **Multithreading** em Java para otimizar o processamento de dados em larga escala, dividindo cargas de trabalho e reduzindo o tempo de execução através do paralelismo.

---

## 📌 Tarefa 01-01: Processamento Multithread com Vetores em Java

O objetivo desta primeira tarefa foi otimizar a inicialização e a análise de um vetor de grande escala de forma paralela.

### 📋 Requisitos da Tarefa
1. Alocar um vetor do tipo `double` com 200 milhões de entradas.
2. Inicializar o vetor com valores aleatórios usando múltiplas threads de forma paralela.
3. Exibir a mensagem `"Encerrou inicializacao"` imediatamente após o preenchimento total do vetor.
4. Computa e exibe a quantidade de valores no vetor que estão no intervalo aberto entre `0.25` e `0.75` ($0.25 < valor < 0.75$).

### 🛠️ Arquitetura e Solução
Para evitar condições de corrida (Race Conditions), o tamanho total do vetor (200.000.000) foi dividido igualmente entre as threads:
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
* **Evitando Concorrência:** Para máxima performance, cada thread gerencia seu próprio array local de contagem (26 posições). Após todas as threads terminarem a leitura via `.join()`, a thread principal junta os totais, evitando gargalos de sincronização durante a leitura.

---

## 🚀 Como Executar as Tarefas

### Pré-requisitos
* Java JDK 11 ou superior instalado.
* Git configurado.

### Executando a Tarefa 01-01
```bash
# Navegue até a pasta da tarefa 1
cd Tarefa_01_01

# Compile o código
javac src/Main.java -d bin

# Execute o programa
java -cp bin Main
