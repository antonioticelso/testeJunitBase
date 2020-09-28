package br.com.tonhao.servicos;

import br.com.tonhao.entidades.Usuario;

public interface EmailService {

    public void notificarAtraso(Usuario usuario);
}
