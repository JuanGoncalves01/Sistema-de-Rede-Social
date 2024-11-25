package com.redesocial.gerenciador;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Comentario;
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
        Post post = new Post(autor, conteudo);
        post.setId(proximoId++);
        posts.add(post);
    }

    public List<Post> listarPosts(Usuario usuario) {
        List<Post> usuarioPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAutor().getId().equals(usuario.getId())) {
                usuarioPosts.add(post);
            }
        }
        return usuarioPosts;
    }

    public void comentar(Post post, Usuario autor, String conteudoComentario) {
        Comentario comentario = new Comentario(autor, conteudoComentario, post);
        post.adicionarComentario(comentario);
    }


    public Post buscarPostPorId(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }
}
