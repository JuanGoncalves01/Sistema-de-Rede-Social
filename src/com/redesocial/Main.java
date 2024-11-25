package com.redesocial;

import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;

public class Main {
    public static void main(String[] args) {
        // Gerenciadores
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
        GerenciadorPosts gerenciadorPosts = new GerenciadorPosts();

        // Cadastro de usuários
        gerenciadorUsuarios.cadastrarUsuario("João Silva", "joao123", "joao@email.com", "123456");
        gerenciadorUsuarios.cadastrarUsuario("Maria Souza", "maria456", "maria@email.com", "654321");

        // Obtenção de usuários
        Usuario joao = gerenciadorUsuarios.listarUsuarios().get(0);
        Usuario maria = gerenciadorUsuarios.listarUsuarios().get(1);

        // Criação de posts
        gerenciadorPosts.criarPost(joao, "Este é meu primeiro post!");
        gerenciadorPosts.criarPost(maria, "Oi, pessoal! Estou adorando essa rede social!");

        // Listar posts de João
        System.out.println("\nPosts de João:");
        for (Post post : gerenciadorPosts.listarPostsPorUsuario(joao)) {
            System.out.println(post);
        }

        // Curtir e comentar nos posts
        Post postJoao = gerenciadorPosts.listarPostsPorUsuario(joao).get(0);
        gerenciadorPosts.curtirPost(postJoao, maria); // Maria curte o post de João
        gerenciadorPosts.comentarPost(postJoao, maria, "Muito legal o seu post, João!");

        // Listar posts de João após interação
        System.out.println("\nPosts de João após interações:");
        for (Post post : gerenciadorPosts.listarPostsPorUsuario(joao)) {
            System.out.println(post);
        }

        // Descurtir o post
        gerenciadorPosts.descurtirPost(postJoao, maria);
        System.out.println("\nPosts de João após descurtir:");
        for (Post post : gerenciadorPosts.listarPostsPorUsuario(joao)) {
            System.out.println(post);
        }
    }
}
