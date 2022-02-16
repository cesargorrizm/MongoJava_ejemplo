package com.becaries.MongoJava.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelicula {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Titulo")
    @Expose
    private String titulo;
    @SerializedName("Titulo original")
    @Expose
    private String tituloOriginal;
    @SerializedName("Ano")
    @Expose
    private String ano;
    @SerializedName("Duracion")
    @Expose
    private String duracion;
    @SerializedName("Pais")
    @Expose
    private String pais;
    @SerializedName("Guion")
    @Expose
    private String guion;
    @SerializedName("Musica")
    @Expose
    private String musica;
    @SerializedName("Fotografia")
    @Expose
    private String fotografia;
    @SerializedName("Reparto")
    @Expose
    private String reparto;
    @SerializedName("Sinopsis")
    @Expose
    private String sinopsis;
    @SerializedName("Trailer")
    @Expose
    private String trailer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Pelicula(String id, String titulo, String tituloOriginal, String ano, String duracion, String pais,
            String guion, String musica, String fotografia, String reparto, String sinopsis, String trailer) {
        this.id = id;
        this.titulo = titulo;
        this.tituloOriginal = tituloOriginal;
        this.ano = ano;
        this.duracion = duracion;
        this.pais = pais;
        this.guion = guion;
        this.musica = musica;
        this.fotografia = fotografia;
        this.reparto = reparto;
        this.sinopsis = sinopsis;
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "Pelicula [ano=" + ano + ", duracion=" + duracion + ", fotografia=" + fotografia + ", guion=" + guion
                + ", id=" + id + ", musica=" + musica + ", pais=" + pais + ", reparto=" + reparto + ", sinopsis="
                + sinopsis + ", titulo=" + titulo + ", tituloOriginal=" + tituloOriginal + ", trailer=" + trailer + "]";
    }
    

}