package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Usuario> curtidas;  // Lista de usuários que curtiram o post
    private List<Comentario> comentarios; // Lista de comentários no post

    public Post(String conteudo, Usuario autor) {
        this.conteudo = conteudo;
        this.autor = autor;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void adicionarCurtida(Usuario usuario) {
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
        }
    }

    public void removerCurtida(Usuario usuario) {
        curtidas.remove(usuario);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", autor=" + autor.getUsername() +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", curtidas=" + curtidas.size() +
                ", comentarios=" + comentarios.size() +
                '}';
    }
}
