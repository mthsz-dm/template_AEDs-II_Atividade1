import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] TAMANHOS_TESTE_GRANDE =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] TAMANHOS_TESTE_MEDIO =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] TAMANHOS_TESTE_PEQUENO = {          3,          6,          12,          24,          48 };
    static final double NANO_TO_MILLI = 1.0/1_000_000;
    static Random aleatorio = new Random(42);
    static long operacoes;

    /**
     * Código de teste 1. Este método percorre somente as posições pares do vetor e conta
     * quantos valores ímpares existem nessas posições. Operação relevante: o cálculo do
     * resto (vetor[i] % 2) somado à resposta, executado n/2 vezes. Complexidade: O(n).
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa a quantidade de números ímpares armazenados nas posições pares do vetor.
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoes++;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método executa um laço externo em que k começa em (n-1) e é
     * dividido por 2 a cada passo (log2(n) iterações); para cada k, o laço interno executa
     * k+1 incrementos. O total é aproximadamente n + n/2 + n/4 + ... ≈ 2n. Operação relevante:
     * o incremento do contador. Complexidade: O(n).
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa o total de iterações executadas pelo laço interno (≈ 2n).
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
                operacoes++;
            }
        }
        return contador;
    }

    /**
     * Código de teste 3. Este método ordena o vetor em ordem crescente usando o algoritmo
     * de ordenação por seleção (Selection Sort). Operação relevante: a comparação entre
     * elementos do vetor, executada n(n-1)/2 vezes. Complexidade: O(n²).
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método calcula o n-ésimo termo da sequência de
     * Fibonacci de forma recursiva ingênua (sem memorização). Operação relevante: cada
     * chamada da função, que ocorre 2*fib(n) - 1 vezes. Complexidade: O(φⁿ) ≈ O(1,618ⁿ), exponencial.
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa o n-ésimo termo da sequência de Fibonacci.
     */
    static int codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido.
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
    }

    /**
     * Imprime uma linha de resultado no console, no formato CSV (separador ';'),
     * para facilitar a cópia para uma planilha eletrônica.
     */
    static void registrar(String algoritmo, long n, long operacoesRealizadas, double tempoMs) {
        System.out.printf("%s;%d;%d;%.4f%n", algoritmo, n, operacoesRealizadas, tempoMs);
    }

    static void testarCodigo1() {
        for (int tamanho : TAMANHOS_TESTE_GRANDE) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo1(vetor);
            long fim = System.nanoTime();
            registrar("Codigo1", tamanho, operacoes, (fim - inicio) * NANO_TO_MILLI);
        }
    }

    static void testarCodigo2() {
        for (int tamanho : TAMANHOS_TESTE_GRANDE) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo2(vetor);
            long fim = System.nanoTime();
            registrar("Codigo2", tamanho, operacoes, (fim - inicio) * NANO_TO_MILLI);
        }
    }

    static void testarCodigo3() {
        for (int tamanho : TAMANHOS_TESTE_MEDIO) {
            int[] vetor = gerarVetor(tamanho);
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo3(vetor);
            long fim = System.nanoTime();
            registrar("Codigo3", tamanho, operacoes, (fim - inicio) * NANO_TO_MILLI);
        }
    }

    static void testarCodigo4() {
        for (int n : TAMANHOS_TESTE_PEQUENO) {
            operacoes = 0;
            long inicio = System.nanoTime();
            codigo4(n);
            long fim = System.nanoTime();
            registrar("Codigo4", n, operacoes, (fim - inicio) * NANO_TO_MILLI);
        }
    }

    public static void main(String[] args) {
        System.out.println("Algoritmo;n;Operacoes;Tempo(ms)");
        testarCodigo1();
        testarCodigo2();
        testarCodigo3();
        testarCodigo4();
    }
}
