package br.com.tonhao.servicos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.tonhao.entidades.Filme;
import br.com.tonhao.entidades.Locacao;
import br.com.tonhao.entidades.Usuario;
import br.com.tonhao.exception.FilmeSemEstoqueException;
import br.com.tonhao.exception.LocadoraException;
import br.com.tonhao.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		if (usuario == null) {
			throw new LocadoraException("Usuario nao selecionado.");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme nao selecionado.");
		}

		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException("Filme sem estoque.");
			}
		}

		Locacao locacao = new Locacao();
//		locacao.setFilmes(List<Filme> filmes);
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for (int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			Double valorFilme = filme.getPrecoLocacao();
			switch (i) {
				case 2: valorFilme = valorFilme * 0.75; break;
				case 3: valorFilme = valorFilme * 0.5; break;
				case 4: valorFilme = valorFilme * 0.25; break;
				case 5: valorFilme = 0d; break;
			}

			valorTotal+= valorFilme;
		}
		locacao.setValor(valorTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
			dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);

		}
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {
		
	}
}