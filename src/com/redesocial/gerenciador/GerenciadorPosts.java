package com.redesocial.gerenciador;

import com.redesocial.modelo.Comentario;
import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPosts {
    private List<Post> posts;
    private int proximoId;

    public GerenciadorPosts() {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
    }

    public void criarPost(Usuario autor, String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do post não pode ser vazio.");
        }
        Post post = new Post(conteudo, autor);
        post.setId(proximoId++);
        posts.add(post);
        autor.adicionarPost(post); // Agora o método existe
    }

    public List<Post> listarPostsPorUsuario(Usuario usuario) {
        List<Post> postsUsuario = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAutor().equals(usuario)) {
                postsUsuario.add(post);
            }
        }
        return postsUsuario;
    }

    public void curtirPost(Post post, Usuario usuario) {
        post.adicionarCurtida(usuario);
    }

    public void descurtirPost(Post post, Usuario usuario) {
        post.removerCurtida(usuario);
    }

    public void comentarPost(Post post, Usuario autor, String conteudo) {
        Comentario comentario = new Comentario(autor, conteudo, post);
        post.adicionarComentario(comentario);
    }
}
