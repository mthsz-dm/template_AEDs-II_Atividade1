# AEDs II - Oficinas e atividade 1 - Desempenho de algoritmos e algoritmos de ordenação 
Oficinas e atividade pontuada realizada em AEDs II, tendo em vista algoritmos de ordenação em sistemas de software e seus desempenhos.

## Aluno 

* Matheus Dias Mendes


## Atividade: contagem de operações

### Como executar

O vetor de 500 milhões de inteiros ocupa cerca de 2 GB, então é preciso aumentar o heap da JVM:

```bash
javac -encoding UTF-8 -d bin src/App.java
java -Xmx4g -cp bin App
```

### Tarefa 0 — Instrumentação

| Algoritmo | O que faz | Operação contada | Operações esperadas | Complexidade |
|---|---|---|---|---|
| `codigo1` | Conta os valores ímpares armazenados nas posições pares do vetor | `resposta += vetor[i] % 2` | n/2 | O(n) |
| `codigo2` | Laço externo com k = n-1, (n-1)/2, ...; laço interno com k+1 iterações | `contador++` | n + n/2 + n/4 + ... ≈ 2n | O(n) |
| `codigo3` | Ordenação por seleção (Selection Sort) | comparação `vetor[j] < vetor[menor]` | n(n-1)/2 | O(n²) |
| `codigo4` | Fibonacci recursivo sem memorização | cada chamada de `codigo4` | 2·fib(n) - 1 | O(φⁿ) ≈ O(1,618ⁿ) |

A contagem é feita pelo atributo estático `operacoes`, zerado antes de cada execução. O tempo é medido com `System.nanoTime()` imediatamente antes e depois da chamada do algoritmo (a geração do vetor fica fora da medição).

### Tarefa 1 — Resultados

Dados brutos em [docs/resultados.csv](docs/resultados.csv); planilha com os gráficos em [docs/resultados.xlsx](docs/resultados.xlsx) (uma aba por algoritmo, com gráficos de operações × n e tempo × n).

| Algoritmo | n | Operações | Tempo (ms) |
|---|---:|---:|---:|
| Código 1 | 31.250.000 | 15.625.000 | 14,24 |
| Código 1 | 62.500.000 | 31.250.000 | 25,42 |
| Código 1 | 125.000.000 | 62.500.000 | 40,38 |
| Código 1 | 250.000.000 | 125.000.000 | 67,56 |
| Código 1 | 500.000.000 | 250.000.000 | 136,40 |
| Código 2 | 31.250.000 | 62.500.007 | 26,19 |
| Código 2 | 62.500.000 | 125.000.007 | 43,06 |
| Código 2 | 125.000.000 | 250.000.007 | 6,12 |
| Código 2 | 250.000.000 | 500.000.007 | 12,12 |
| Código 2 | 500.000.000 | 1.000.000.007 | 24,26 |
| Código 3 | 12.500 | 78.118.750 | 60,31 |
| Código 3 | 25.000 | 312.487.500 | 225,23 |
| Código 3 | 50.000 | 1.249.975.000 | 514,11 |
| Código 3 | 100.000 | 4.999.950.000 | 3.577,45 |
| Código 3 | 200.000 | 19.999.900.000 | 14.187,68 |
| Código 4 | 3 | 3 | 0,0012 |
| Código 4 | 6 | 15 | 0,0007 |
| Código 4 | 12 | 287 | 0,0079 |
| Código 4 | 24 | 92.735 | 0,3394 |
| Código 4 | 48 | 9.615.053.951 | 10.705,78 |

