package br.com.tonhao.exception;

public class FilmeSemEstoqueException extends Exception {

    public static final Long serialVersionUID = -244245625L;

//    private String message = "Filme sem estoque.";

    public FilmeSemEstoqueException(String message) {
        super(message);
    }
}
