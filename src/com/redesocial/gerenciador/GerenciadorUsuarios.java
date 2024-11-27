package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;
    private int proximoId;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }

    public void cadastrarUsuario(String nome, String username, String email, String senha) {
        Usuario usuario = new Usuario(nome, username, email, senha);
        usuario.setId(proximoId++);
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // Para fazer o login

    public Usuario loginUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;  // Retorna null caso não encontre usuário ou senha incorretos
    }public Usuario login(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;  // Retorna null caso não encontre o usuário
    }

}
