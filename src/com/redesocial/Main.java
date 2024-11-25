package com.redesocial;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        MenuPrincipal menu = new MenuPrincipal(gerenciadorUsuarios, gerenciadorPosts);
        menu.exibirMenu();
    }
}
