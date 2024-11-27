package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.gerenciador.GerenciadorPosts;
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
        Usuario usuarioLogado = null;  // Variável para armazenar o usuário logado

        while (true) {
            if (usuarioLogado == null) {
                // Exibe o menu principal (login ou cadastro)
                System.out.println("1. Login");
                System.out.println("2. Cadastrar novo usuário");
                System.out.println("3. Sair");
                int opcao = obterOpcaoMenu(3);  // Permitir opções entre 1 e 3

                switch (opcao) {
                    case 1:
                        usuarioLogado = login();
                        break;
                    case 2:
                        cadastrarUsuario();
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } else {
                // Menu do usuário logado
                System.out.println("Bem-vindo, " + usuarioLogado.getNome());
                System.out.println("1. Criar Post");
                System.out.println("2. Ver Meu Perfil");
                System.out.println("3. Buscar Usuários");
                System.out.println("4. Gerenciar Amizades");
                System.out.println("5. Ver Feed de Notícias");
                System.out.println("6. Logout");
                int opcao = obterOpcaoMenu(6);  // Permitir opções entre 1 e 6

                switch (opcao) {
                    case 1:
                        criarPost(usuarioLogado);
                        break;
                    case 2:
                        verPerfil(usuarioLogado);
                        break;
                    case 3:
                        buscarUsuarios();
                        break;
                    case 4:
                        gerenciarAmizades(usuarioLogado);
                        break;
                    case 5:
                        verFeed(usuarioLogado);
                        break;
                    case 6:
                        usuarioLogado = null;  // Logout
                        System.out.println("Logout realizado com sucesso!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }

    private int obterOpcaoMenu(int maxOpcao) {
        int opcao = -1;
        while (opcao < 1 || opcao > maxOpcao) {
            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer de nova linha
                if (opcao < 1 || opcao > maxOpcao) {
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();  // Limpar o buffer de entrada
            }
        }
        return opcao;
    }

    private Usuario login() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioLogado = gerenciadorUsuarios.login(username, senha);  // Método para fazer login
        if (usuarioLogado != null) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Nome de usuário ou senha incorretos!");
        }
        return usuarioLogado;
    }

    private void cadastrarUsuario() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        gerenciadorUsuarios.cadastrarUsuario(nome, username, email, senha);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void criarPost(Usuario usuarioLogado) {
        System.out.print("Digite o conteúdo do post: ");
        String conteudo = scanner.nextLine();
        gerenciadorPosts.criarPost(usuarioLogado, conteudo);
        System.out.println("Post criado com sucesso!");
    }

    private void verPerfil(Usuario usuarioLogado) {
        System.out.println("Perfil de " + usuarioLogado.getNome());
        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());

        // Exibir lista de posts
        System.out.println("Posts:");
        List<Post> posts = gerenciadorPosts.listarPosts(usuarioLogado);
        if (posts.isEmpty()) {
            System.out.println("Nenhum post encontrado.");
        } else {
            for (Post post : posts) {
                System.out.println(post);
            }
        }

        // Aqui você pode adicionar mais funcionalidades, como editar perfil ou ver amigos
    }

    private void buscarUsuarios() {
        // Lógica de busca de usuários (caso deseje implementar isso mais tarde)
        System.out.println("Procurando usuários...");
    }

    private void gerenciarAmizades(Usuario usuarioLogado) {
        // Lógica de gerenciamento de amizades
        System.out.println("Gerenciando amizades...");
    }

    private void verFeed(Usuario usuarioLogado) {
        // Exibir o feed de notícias (posts de amigos ou do próprio usuário)
        System.out.println("Feed de notícias...");
    }
}
