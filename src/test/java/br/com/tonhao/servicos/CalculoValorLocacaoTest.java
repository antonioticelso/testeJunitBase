package br.com.tonhao.servicos;

import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.exception.FilmeSemEstoqueException;
import br.com.tonhao.exception.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CalculoValorLocacaoTest {
    private LocacaoService locacaoService;

    @Before
    public void setup() {
        locacaoService = new LocacaoService();
    }

    private List<Filme> filmes;

    private Double valorLocacao;

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
        Assert.assertThat(resultado.getValor(), CoreMatchers.is(valorLocacao));

    }

}
