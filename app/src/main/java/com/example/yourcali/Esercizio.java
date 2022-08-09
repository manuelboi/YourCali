package com.example.yourcali;

import android.media.Image;

public class Esercizio {
    private String nome, descrizione;
    private Integer serie, ripetizioni, riposo;
    private Image immagine;

    public Esercizio (String nome, String descrizione, Image immagine){
        this.nome = nome;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }

    public Esercizio (String nome, Integer ripetizioni, Integer serie){
        this.nome = nome;
        this.ripetizioni = ripetizioni;
        this.serie = serie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(Integer ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public Integer getRiposo() {
        return riposo;
    }

    public void setRiposo(Integer riposo) {
        this.riposo = riposo;
    }

    public Image getImmagine() {
        return immagine;
    }

    public void setImmagine(Image immagine) {
        this.immagine = immagine;
    }

}
