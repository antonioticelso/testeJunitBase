package br.com.tonhao.daos;

import br.com.tonhao.entidades.Locacao;

import java.util.List;

public interface LocacaoDao {

    public void salvar(Locacao locacao);

    public List<Locacao> obterLocacoesPendentes();
}
