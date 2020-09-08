package br.com.tonhao.servicos;

import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.exception.FilmeSemEstoqueException;
import br.com.tonhao.exception.LocadoraException;
import br.com.tonhao.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Date;

public class LocacaoServiceTest {

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deveFazerLocacao() throws Exception {

//        cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");
        Filme filme = new Filme("Equilibriun", 2, 5.0);

//        acao
        Locacao locacao = service.alugarFilme(usuario, filme);

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
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");
        Filme filme = new Filme("Equilibriun", 0, 5.0);

//        acao
        service.alugarFilme(usuario, filme);

    }

    @Test
    public void deveFazerLocacaoSemEstoque_2() {

//        cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");
        Filme filme = new Filme("Equilibriun", 0, 5.0);

//        acao
        try {
            service.alugarFilme(usuario, filme);
            Assert.fail("Deveria ter lancado uma excecao!");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme sem estoque."));
            e.printStackTrace();
        }

    }

    @Test
    public void deveFazerLocacaoSemEstoque_3() throws Exception {

//        cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");
        Filme filme = new Filme("Equilibriun", 0, 5.0);

        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque.");

//        acao
        service.alugarFilme(usuario, filme);

    }

    @Test
    public void deveFazerLocacaoSemUsuario() throws FilmeSemEstoqueException {
//        cenario
        LocacaoService service = new LocacaoService();
        Filme filme = new Filme("Equilibriun", 3, 5.0);

//        acao
        try {
            service.alugarFilme(null, filme);
            Assert.fail("Deveria ter lancado uma excecao!");
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuario nao selecionado."));
            e.printStackTrace();
        }

    }

    @Test
    public void deveFazerLocacaoSemFilme() throws FilmeSemEstoqueException, LocadoraException {

//        cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme nao selecionado.");

//        acao
        service.alugarFilme(usuario, null);

    }

}
