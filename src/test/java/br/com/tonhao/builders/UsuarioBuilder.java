package br.com.tonhao.builders;

import br.com.tonhao.entidades.Usuario;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioBuilder {

    private Usuario usuario;

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Godolfredo");
        return builder;
    }

    public Usuario agora() {
        return usuario;
    }
}
