package br.com.tonhao.servicos;

import br.com.tonhao.daos.LocacaoDao;
import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.exception.FilmeSemEstoqueException;
import br.com.tonhao.exception.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
    private LocacaoService locacaoService;
    private SPCService spc;
    private LocacaoDao dao;

    @Parameterized.Parameter
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;

    @Parameterized.Parameter(value = 2)
    public String cenario;

    @Before
    public void setup() {
        locacaoService = new LocacaoService();

        dao = Mockito.mock(LocacaoDao.class);
        locacaoService.setLocacaoDao(dao);

        spc = Mockito.mock(SPCService.class);
        locacaoService.setSPCService(spc);


    }

    private static Filme filme1 = new Filme("Piratas do Caribe", 2, 4.0);
    private static Filme filme2 = new Filme("Senhor dos Aneis", 5, 4.0);
    private static Filme filme3 = new Filme("O Livro de Heli", 3, 4.0);
    private static Filme filme4 = new Filme("Jack Rider", 3, 4.0);
    private static Filme filme5 = new Filme("Velozes e Furiosos", 2, 4.0);
    private static Filme filme6 = new Filme("Matrix", 1, 4.0);

    @Parameterized.Parameters(name = "Teste {2}")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[] [] {
                {Arrays.asList(filme1, filme2, filme3), 11.0, "25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5,filme6), 14.0, "100%"}
        });
    }

    @Test
    public void devePagarMenosNoFilme_3() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(valorLocacao));

    }

}
