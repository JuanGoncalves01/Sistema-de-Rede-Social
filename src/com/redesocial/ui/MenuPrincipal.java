package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;

import java.util.Scanner;

public class MenuPrincipal {

    private GerenciadorUsuarios gerenciadorUsuarios;

    // Construtor
    public MenuPrincipal() {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
    }

    // 1. Exibir Menu Principal
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== Bem-vindo à Rede Social! =====");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // 2. Fazer Login
    private void fazerLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o username: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.buscarPorUsername(username);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            System.out.println("Login realizado com sucesso! Bem-vindo(a), " + usuario.getNome());
            exibirMenuLogado(usuario);
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    // 3. Cadastrar Usuário
    private void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        // Coletando dados do usuário
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite um username: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite uma senha: ");
        String senha = scanner.nextLine();

        // Criando um novo usuário com os dados informados
        Usuario novoUsuario = new Usuario(nome, username, email, senha);

        // Cadastrando o usuário no sistema
        gerenciadorUsuarios.cadastrar(novoUsuario);

        // Confirmando que o usuário foi cadastrado
        System.out.println("Usuário cadastrado com sucesso!");
    }


    // 4. Exibir Menu Logado
    private void exibirMenuLogado(Usuario usuario) {
        MenuUsuario menuUsuario = new MenuUsuario(usuario);
        menuUsuario.exibirMenu();
    }
}
