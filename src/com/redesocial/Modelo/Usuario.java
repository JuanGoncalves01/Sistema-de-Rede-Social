


package com.redesocial.Modelo;

import java.util.ArrayList;
import java.util.List;

    public class Usuario {
        private Integer id;
        private String nome;
        private String username;
        private String email;
        private String senha;
        private List<Usuario> amigos;

        public Usuario(String nome, String username, String email, String senha) {
            this.nome = nome;
            this.username = username;
            this.email = email;
            this.senha = senha;
            this.amigos = new ArrayList<>();
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

        public List<Usuario> getAmigos() {
            return amigos;
        }

        public void adicionarAmigo(Usuario amigo) {
            amigos.add(amigo);
        }

        @Override
        public String toString() {
            return "Usuario{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
