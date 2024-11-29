package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;

import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private Usuario usuario;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorPosts gerenciadorPosts;

    // Construtor
    public MenuUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.gerenciadorUsuarios = new GerenciadorUsuarios(); // Idealmente injetar dependências
        this.gerenciadorPosts = new GerenciadorPosts();
    }

    // 1. Exibir Menu do Usuário
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== Menu do Usuário =====");
            System.out.println("1. Criar Post");
            System.out.println("2. Ver Meu Perfil");
            System.out.println("3. Buscar Usuários");
            System.out.println("4. Gerenciar Amizades");
            System.out.println("5. Ver Feed de Notícias");
            System.out.println("0. Logout");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    criarPost();
                    break;
                case 2:
                    verPerfil();
                    break;
                case 3:
                    buscarUsuarios();
                    break;
                case 4:
                    gerenciarAmizades();
                    break;
                case 5:
                    verFeedNoticias();
                    break;
                case 0:
                    System.out.println("Logout realizado com sucesso.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // 2. Criar Post
    private void criarPost() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o conteúdo do post: ");
        String conteudo = scanner.nextLine();

        // Cria um novo post com o usuário logado
        Post novoPost = new Post(0, usuario, conteudo);

        // Adiciona o post ao sistema (usando o Gerenciador de Posts)
        gerenciadorPosts.criar(novoPost);

        System.out.println("Post criado com sucesso!");
    }


    // 3. Ver Perfil
    private void verPerfil() {
        System.out.println("===== Meu Perfil =====");
        System.out.println(usuario);
        System.out.println("Amigos:");
        usuario.getAmigos().forEach(amigo -> System.out.println("- " + amigo.getNome()));

        System.out.println("Posts:");
        usuario.getPosts().forEach(post -> System.out.println("- " + post.getConteudo() + " (Publicado em " + post.getDataPublicacao() + ")"));
    }

    // 4. Buscar Usuários
    private void buscarUsuarios() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do usuário para buscar: ");
        String nome = scanner.nextLine();

        List<Usuario> usuariosEncontrados = gerenciadorUsuarios.buscarPorNome(nome);

        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com o nome fornecido.");
        } else {
            System.out.println("Usuários encontrados:");
            usuariosEncontrados.forEach(usuario -> System.out.println("- " + usuario.getNome() + " (" + usuario.getUsername() + ")"));
        }
    }

    // 5. Gerenciar Amizades
    private void gerenciarAmizades() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Gerenciar Amizades =====");
        System.out.println("1. Adicionar Amigo");
        System.out.println("2. Remover Amigo");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        System.out.print("Digite o username do amigo: ");
        String username = scanner.nextLine();
        Usuario amigo = gerenciadorUsuarios.buscarPorUsername(username);

        if (amigo == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (opcao == 1) {
            usuario.adicionarAmigo(amigo);
            gerenciadorUsuarios.adicionarAmizade(usuario.getId(), amigo.getId());
            System.out.println("Amigo adicionado com sucesso.");
        } else if (opcao == 2) {
            usuario.removerAmigo(amigo);
            gerenciadorUsuarios.removerAmizade(usuario.getId(), amigo.getId());
            System.out.println("Amigo removido com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    // 6. Ver Feed de Notícias
    private void verFeedNoticias() {
        System.out.println("===== Feed de Notícias =====");
        List<Post> feed = gerenciadorPosts.listarPorUsuario(usuario.getId());

        if (feed.isEmpty()) {
            System.out.println("Nenhum post encontrado no feed.");
        } else {
            feed.forEach(post -> {
                System.out.println("Autor: " + post.getAutor().getNome());
                System.out.println("Conteúdo: " + post.getConteudo());
                System.out.println("Publicado em: " + post.getDataPublicacao());
                System.out.println("Curtidas: " + post.getCurtidas().size());
                System.out.println("Comentários: " + post.getComentarios().size());
                System.out.println("----------------------------------");
            });
        }
    }
}
