package com.redesocial.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private List<Amizade> amizades;  // Lista de amizades

    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.amizades = new ArrayList<>();  // Inicializa a lista de amizades
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Amizade> getAmizades() {
        return amizades;
    }

    public void adicionarAmizade(Usuario amigo) {
        if (!this.amizades.contains(new Amizade(this, amigo))) {
            Amizade amizade = new Amizade(this, amigo);
            this.amizades.add(amizade);
            amigo.adicionarAmizade(this);  // Amizade é bidirecional
        }
    }

    public void removerAmizade(Usuario amigo) {
        Amizade amizade = new Amizade(this, amigo);
        this.amizades.remove(amizade);
        amigo.removerAmizade(this);  // Remove a amizade bidirecionalmente
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nome='" + nome + "', username='" + username + "', email='" + email + "'}";
    }
}
