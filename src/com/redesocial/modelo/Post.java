package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

    private Integer id;
    private Usuario autor;
    private String conteudo;
    private LocalDateTime dataPublicacao;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    // Construtor completo
    public Post(Integer id, Usuario autor, String conteudo) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = LocalDateTime.now();
        this.curtidas = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    // Getters e setters
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

    public void setCurtidas(List<Usuario> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    // Adicionar curtida
    public void adicionarCurtida(Usuario usuario) {
        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
            System.out.println(usuario.getNome() + " curtiu o post!");
        } else {
            System.out.println("Usuário já curtiu o post.");
        }
    }

    // Remover curtida
    public void removerCurtida(Usuario usuario) {
        if (curtidas.contains(usuario)) {
            curtidas.remove(usuario);
            System.out.println(usuario.getNome() + " removeu a curtida.");
        } else {
            System.out.println("Usuário não havia curtido o post.");
        }
    }

    // Adicionar comentário
    public void adicionarComentario(Comentario comentario) {
        comentarios.add(comentario);
        System.out.println("Comentário adicionado com sucesso!");
    }

    // toString
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", autor=" + autor.getNome() +
                ", conteudo='" + conteudo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", curtidas=" + curtidas.size() +
                ", comentarios=" + comentarios.size() +
                '}';
    }

    // equals e hashCode baseados no id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
