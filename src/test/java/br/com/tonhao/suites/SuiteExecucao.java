package br.com.tonhao.suites;

import br.com.tonhao.servicos.CalculadoraTest;
import br.com.tonhao.servicos.CalculoValorLocacaoTest;
import br.com.tonhao.servicos.LocacaoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuiteExecucao {
//    pode ser criado o DB
}
