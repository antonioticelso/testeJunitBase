package br.com.tonhao.servicos;

import br.com.tonhao.exception.NaoPodeDividirPorZeroException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

public class CalculadoraTest {

    private Calculadora calc;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        calc = new Calculadora();
        System.out.println("before");

    }

    @Test
    public void deveSomarDoisValores() {
//        cenario
        int a = 5;
        int b = 4;

//        acao
        int result = calc.somar(a, b);

//        verificacao
        Assert.assertEquals(9, result);

    }

    @Test
    public void deveSubtrairDoisValores() {
//        cenario
        int a = 8;
        int b = 5;

//        acao
        int resultado = calc.subtracao(a, b);

//        verificacao
        Assert.assertThat(resultado, CoreMatchers.is(3));

    }

    @Test
    public void deveMultiplicarDoisValores() {
//        cenario
        int a = 4;
        int b = 3;

//        acao
        double result = calc.multiplicacao(a, b);

//        verificacao
        error.checkThat(result, CoreMatchers.is(12.0));

    }

    @Test
    public void deveDivisaoDoisValores() throws NaoPodeDividirPorZeroException {
//        cenario
        int a = 15;
        int b = 5;

//        acao
        double result = calc.divisao(a, b);

//        verificacao
        error.checkThat(result, CoreMatchers.is(3.0));

    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveDividirPorZero() throws NaoPodeDividirPorZeroException {
//        cenario
        int a = 10;
        int b = 0;

//        acao
        double result = calc.divisao(a, b);

//        verificacao
        Assert.assertThat(result, CoreMatchers.is(1));

    }

    @Test
    public void deveDividirPorZero2() throws NaoPodeDividirPorZeroException {
//        cenario
        int a = 10;
        int b = 0;

        exception.expect(Exception.class);
        exception.expectMessage("Qualquer numero nao pode ser dividido por zero.");

//        acao
        calc.divisao(a, b);

    }

}
