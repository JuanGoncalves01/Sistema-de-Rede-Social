package com.redesocial.gerenciador;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorPosts {

    private List<Post> posts;
    private int proximoId;

    // Construtor
    public GerenciadorPosts() {
        this.posts = new ArrayList<>();
        this.proximoId = 1; // Começa o ID a partir de 1
    }

    // 1. Método para criar um novo post
    public void criar(Post post) {
        validarPost(post);
        post.setId(proximoId++);
        posts.add(post);
    }

    // 2. Buscar post por ID
    public Post buscarPorId(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null; // Retorna null se o post não for encontrado
    }

    // 3. Listar posts por ID de usuário
    public List<Post> listarPorUsuario(int idUsuario) {
        List<Post> postsUsuario = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAutor().getId() == idUsuario) {
                postsUsuario.add(post);
            }
        }
        return postsUsuario;
    }

    // 4. Curtir um post
    public void curtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = buscarUsuario(idUsuario);

        if (post != null && usuario != null) {
            post.adicionarCurtida(usuario);
        }
    }

    // 5. Descurtir um post
    public void descurtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        Usuario usuario = buscarUsuario(idUsuario);

        if (post != null && usuario != null) {
            post.removerCurtida(usuario);
        }
    }

    // 6. Comentar em um post
    public void comentar(Comentario comentario) {
        Post post = buscarPorId(comentario.getPost().getId());

        if (post != null) {
            post.adicionarComentario(comentario);
        }
    }

    // 7. Deletar um post
    public boolean deletar(int id) {
        Post post = buscarPorId(id);
        if (post != null) {
            posts.remove(post);
            return true;
        }
        return false;
    }

    // 8. Validar se o post é válido (exemplo de validação simples)
    private void validarPost(Post post) {
        if (post.getConteudo() == null || post.getConteudo().isEmpty()) {
            throw new IllegalArgumentException("Conteúdo do post não pode ser vazio.");
        }
    }

    // Método auxiliar para buscar um usuário pelo ID (pode ser alterado de acordo com sua lógica de usuários)
    private Usuario buscarUsuario(int idUsuario) {
        // Este método precisa ser implementado ou ajustado de acordo com o sistema de gerenciamento de usuários
        // Aqui estamos assumindo que o método buscarPorId existe na classe GerenciadorUsuarios
        return null; // Implemente a lógica de busca de usuários conforme sua necessidade
    }

    // Getters e Setters
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getProximoId() {
        return proximoId;
    }

    public void setProximoId(int proximoId) {
        this.proximoId = proximoId;
    }
}
