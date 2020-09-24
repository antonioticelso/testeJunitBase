package br.com.tonhao.servicos;

import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.exception.FilmeSemEstoqueException;
import br.com.tonhao.exception.LocadoraException;
import br.com.tonhao.matchers.DiaSemanaMatcher;
import br.com.tonhao.matchers.MatchersProprios;
import br.com.tonhao.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocacaoServiceTest {

    private LocacaoService locacaoService;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
//        System.out.println("Before");
        locacaoService = new LocacaoService();
    }

    @Test
    public void deveFazerLocacao() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Equilibriun", 2, 5.0));

//        acao
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertEquals(5.0, locacao.getValor(), 0.01);
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(5.0));
        error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));

        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));

        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
                CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
                CoreMatchers.is(true));
    }

    @Test(expected = Exception.class)
    public void deveFazerLocacaoSemEstoque() throws Exception {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Equilibriun", 0, 5.0));

//        acao
        locacaoService.alugarFilme(usuario, filmes);

    }

    @Test
    public void deveFazerLocacaoSemEstoque_2() {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Equilibriun", 0, 5.0));

//        acao
        try {
            locacaoService.alugarFilme(usuario, filmes);
            Assert.fail("Deveria ter lancado uma excecao!");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque."));
            e.printStackTrace();
        }

    }

    @Test
    public void deveFazerLocacaoSemEstoque_3() throws Exception {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Equilibriun", 0, 5.0));

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque.");

//        acao
        locacaoService.alugarFilme(usuario, filmes);

    }

    @Test
    public void deveFazerLocacaoSemUsuario() throws FilmeSemEstoqueException {
//        cenario
        List<Filme> filmes = Arrays.asList(new Filme("Equilibriun", 3, 5.0));

//        acao
        try {
            locacaoService.alugarFilme(null, filmes);
            Assert.fail("Deveria ter lancado uma excecao!");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario nao selecionado."));
            e.printStackTrace();
        }

    }

    @Test
    public void deveFazerLocacaoSemFilme() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme nao selecionado.");

//        acao
        locacaoService.alugarFilme(usuario, null);

    }

    @Test
    public void devePagarMenosNoFilme_3() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Piratas do Caribe", 2, 4.0),
                new Filme("Senhor dos Aneis", 5, 4.0),
                new Filme("Matrix", 1, 4.0));

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(11.0));

    }

    @Test
    public void devePagarMenosNoFilme_4() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Piratas do Caribe", 2, 4.0),
                new Filme("Senhor dos Aneis", 5, 4.0),
                new Filme("O Livro de Heli", 3, 4.0),
                new Filme("Matrix", 1, 4.0));

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(13.0));

    }

    @Test
    public void devePagarMenosNoFilme_5() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Piratas do Caribe", 2, 4.0),
                new Filme("Senhor dos Aneis", 5, 4.0),
                new Filme("O Livro de Heli", 3, 4.0),
                new Filme("Jack Rider", 3, 4.0),
                new Filme("Matrix", 1, 4.0));

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));

    }

    @Test
    public void devePagarMenosNoFilme_6() throws FilmeSemEstoqueException, LocadoraException {
//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Piratas do Caribe", 2, 4.0),
                new Filme("Senhor dos Aneis", 5, 4.0),
                 new Filme("O Livro de Heli", 3, 4.0),
                new Filme("Jack Rider", 3, 4.0),
                new Filme("Velozes e Furiosos", 2, 4.0),
                new Filme("Matrix", 1, 4.0));

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));

    }

    @Test
    public void deveDevolverDepoisDoDomingo() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

//        cenario
        Usuario usuario = new Usuario("Jorget");
        List<Filme> filmes = Arrays.asList(new Filme("Piratas do Caribe", 2, 4.0),
                new Filme("Jack Rider", 3, 4.0),
                new Filme("Matrix", 1, 4.0));

//        acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);

//        verificacao
        Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
        Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiNumaSegunda());

    }

}
