package br.com.tonhao.builders;

import br.com.tonhao.entidades.Filme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmeBuilder {

    private Filme filme;

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setNome("Vingadores Ultimate");
        builder.filme.setEstoque(0);
        builder.filme.setPrecoLocacao(4.0);
        return builder;

    }

    public Filme novo() {
        return filme;
    }

}
