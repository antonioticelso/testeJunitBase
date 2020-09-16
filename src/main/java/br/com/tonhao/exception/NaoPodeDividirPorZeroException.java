package br.com.tonhao.exception;

public class NaoPodeDividirPorZeroException extends Exception {

    public static final Long serialVersionUID = -34567865625L;

    public NaoPodeDividirPorZeroException(String message) {
        super(message);
    }


//    public DividirPorZeroNao(String message) {
//        super(message);
//    }
}
