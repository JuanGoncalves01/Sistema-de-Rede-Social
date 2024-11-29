package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GerenciadorUsuarios {

    private List<Usuario> usuarios;
    private int proximoId;

    // Construtor
    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1; // Inicializa o ID a partir de 1
    }

    // 1. Cadastrar usuário
    public void cadastrar(Usuario usuario) {
        validarUsuario(usuario);
        usuario.setId(proximoId++);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso: " + usuario);
    }

    // 2. Buscar por ID
    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 3. Buscar por username
    public Usuario buscarPorUsername(String username) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }

    // 4. Buscar por nome
    public List<Usuario> buscarPorNome(String nome) {
        return usuarios.stream()
                .filter(usuario -> usuario.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 5. Atualizar usuário
    public boolean atualizar(Usuario usuario) {
        Optional<Usuario> existente = usuarios.stream()
                .filter(u -> u.getId().equals(usuario.getId()))
                .findFirst();

        if (existente.isPresent()) {
            Usuario usuarioExistente = existente.get();
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            System.out.println("Usuário atualizado com sucesso.");
            return true;
        } else {
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }

    // 6. Deletar usuário
    public boolean deletar(int id) {
        return usuarios.removeIf(usuario -> usuario.getId() == id);
    }

    // 7. Adicionar amizade
    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 != null && usuario2 != null) {
            usuario1.adicionarAmigo(usuario2);
            usuario2.adicionarAmigo(usuario1);
        } else {
            System.out.println("Um ou ambos os usuários não foram encontrados.");
        }
    }

    // 8. Remover amizade
    public void removerAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);

        if (usuario1 != null && usuario2 != null) {
            usuario1.removerAmigo(usuario2);
            usuario2.removerAmigo(usuario1);
        } else {
            System.out.println("Um ou ambos os usuários não foram encontrados.");
        }
    }

    // 9. Validar usuário
    private void validarUsuario(Usuario usuario) {
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia.");
        }
    }

}

