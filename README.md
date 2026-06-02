```markdown
# Tarefa 01-01: Processamento Multithread com Vetores em Java

Este projeto foi desenvolvido como um estudo prático sobre **Programação Concorrente e Multithreading** em Java. O objetivo principal é otimizar a inicialização e o processamento de um vetor de grande escala (200 milhões de posições) dividindo a carga de trabalho entre múltiplas threads.

## 📋 Requisitos da Tarefa

O programa atende estritamente aos seguintes critérios:
1. Aloca um vetor do tipo `double` com 200 milhões de entradas.
2. Inicializa o vetor com valores aleatórios usando múltiplas threads de forma paralela.
3. Exibe a mensagem `"Encerrou inicializacao"` imediatamente após o preenchimento total do vetor.
4. Computa e exibe a quantidade de valores no vetor que estão no intervalo aberto entre `0.25` e `0.75` (0.25 < valor < 0.75).

---

## 🛠️ Arquitetura e Solução

Para evitar que uma thread sobrescreva o trabalho da outra, a divisão do processamento foi estruturada da seguinte forma:

* **Divisão de Escopo:** O tamanho total do vetor (200.000.000) é dividido ao meio. 
* **Thread 1 (t1):** Fica responsável por preencher o intervalo de índices de `0` até `100.000.000`.
* **Thread 2 (t2):** Fica responsável por preencher o intervalo de índices de `100.000.000` até `200.000.000`.
* **Sincronização (`join`):** A thread principal (`main`) utiliza o método `.join()` para aguardar o término das threads de inicialização antes de exibir a mensagem de conclusão e iniciar a contagem dos dados, garantindo a consistência dos dados.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* Java JDK 11 ou superior instalado.
* Git (opcional, para clonar).

### Passo a Passo

1. **Clone o repositório ou navegue até a pasta do projeto:**
   ```bash
   cd caminho/para/o/projeto/thread_estudo

```

2. **Compile o arquivo Java:**
```bash
javac src/Main.java -d bin

```


3. **Execute o programa:**
```bash
java -cp bin Main

```



---

## 📦 Commits Estruturados

O histórico do Git deste projeto foi construído utilizando boas práticas de mercado baseadas em *Conventional Commits*:

* `feat: Threads funcionando` — Implementação da lógica de concorrência, divisão de escopo do vetor e filtragem condicional dos dados.
