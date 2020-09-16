package br.com.tonhao.servicos;

import br.com.tonhao.exception.NaoPodeDividirPorZeroException;

public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public double multiplicacao(int a, int b) {
        return a * b;
    }

    public double divisao(int a, int b) throws NaoPodeDividirPorZeroException {
        if (b == 0) {
            throw new NaoPodeDividirPorZeroException("Qualquer numero nao pode ser dividido por zero.");
        }
        return a / b;
    }

}
