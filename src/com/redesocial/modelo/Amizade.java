package com.redesocial.modelo;

import java.time.LocalDateTime;

public class    Amizade {
    private Usuario usuario1;
    private Usuario usuario2;
    private LocalDateTime dataAmizade;
    private String status; // Por exemplo: "pendente", "confirmada"

    public Amizade(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.dataAmizade = LocalDateTime.now();
        this.status = "pendente"; // Inicia como "pendente"
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public LocalDateTime getDataAmizade() {
        return dataAmizade;
    }

    public void setDataAmizade(LocalDateTime dataAmizade) {
        this.dataAmizade = dataAmizade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Amizade{usuario1=" + usuario1.getNome() + ", usuario2=" + usuario2.getNome() +
                ", dataAmizade=" + dataAmizade + ", status='" + status + "'}";
    }
}
