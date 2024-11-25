package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorPosts gerenciadorPosts;
    private Scanner scanner;

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorPosts gerenciadorPosts) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.gerenciadorPosts = gerenciadorPosts;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Criar Post");
            System.out.println("4. Ver Posts");
            System.out.println("5. Comentar Post");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer de nova linha

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    criarPost();
                    break;
                case 4:
                    verPosts();
                    break;
                case 5:
                    comentarPost();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o username: ");
        String username = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        gerenciadorUsuarios.cadastrarUsuario(nome, username, email, senha);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = gerenciadorUsuarios.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            System.out.println("Lista de usuários:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    private void criarPost() {
        System.out.print("Digite o ID do usuário: ");
        String input = scanner.nextLine();  // Captura a entrada como String

        // Tenta converter a entrada para inteiro
        int idUsuario = -1;  // Valor padrão inválido
        try {
            idUsuario = Integer.parseInt(input);  // Converte a string para inteiro
        } catch (NumberFormatException e) {
            System.out.println("Erro: O ID do usuário deve ser um número inteiro.");
            return;  // Sai do método caso a conversão falhe
        }

        // Verifica se o usuário com esse ID existe
        Usuario usuario = gerenciadorUsuarios.buscarPorId(idUsuario);
        if (usuario != null) {
            System.out.print("Digite o conteúdo do post: ");
            String conteudo = scanner.nextLine();
            gerenciadorPosts.criarPost(usuario, conteudo);
            System.out.println("Post criado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void verPosts() {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer de nova linha
        Usuario usuario = gerenciadorUsuarios.buscarPorId(idUsuario);

        if (usuario != null) {
            List<Post> posts = gerenciadorPosts.listarPosts(usuario);
            if (posts.isEmpty()) {
                System.out.println("Não há posts.");
            } else {
                for (Post post : posts) {
                    System.out.println(post);
                    if (post.getComentarios().isEmpty()) {
                        System.out.println("Nenhum comentário.");
                    } else {
                        for (Comentario comentario : post.getComentarios()) {
                            System.out.println("    " + comentario);
                        }
                    }
                }
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void comentarPost() {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer de nova linha
        Usuario usuario = gerenciadorUsuarios.buscarPorId(idUsuario);

        if (usuario != null) {
            System.out.print("Digite o ID do post para comentar: ");
            String inputPost = scanner.nextLine();  // Captura a entrada como String

            // Tentando converter a entrada para um número inteiro
            int idPost = -1;  // Valor padrão inválido
            try {
                idPost = Integer.parseInt(inputPost);  // Converte a string para inteiro
            } catch (NumberFormatException e) {
                System.out.println("Erro: O ID do post deve ser um número inteiro.");
                return;  // Sai do método se a conversão falhar
            }

            Post post = gerenciadorPosts.buscarPostPorId(idPost);

            if (post != null) {
                System.out.print("Digite o conteúdo do comentário: ");
                String conteudoComentario = scanner.nextLine();
                gerenciadorPosts.comentar(post, usuario, conteudoComentario);
                System.out.println("Comentário adicionado com sucesso!");
            } else {
                System.out.println("Post não encontrado.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

}
