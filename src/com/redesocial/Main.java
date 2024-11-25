package com.redesocial;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;

public class Main {
    public static void main(String[] args) {
        GerenciadorUsuarios gerenciador = new GerenciadorUsuarios();

        // Cadastro de usuários
        gerenciador.cadastrarUsuario("João Silva", "joao123", "joao@email.com", "123456");
        gerenciador.cadastrarUsuario("Maria Souza", "maria456", "maria@email.com", "654321");

        // Listar usuários cadastrados
        for (Usuario usuario : gerenciador.listarUsuarios()) {
            System.out.println(usuario);
        }
    }
}
