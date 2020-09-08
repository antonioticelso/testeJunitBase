package br.com.tonhao.servicos;

import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.utils.DataUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LocacaoServiceTest {

    @Test
    public void deveFazerLocacao() {

//        cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Jorget");
        Filme filme = new Filme("Equilibriun", 2, 5.0);

//        acao
        Locacao locacao = service.alugarFilme(usuario, filme);

//        verificacao
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

}
