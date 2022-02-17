package com.becaries.MongoJava.modelos;

public class Prota {
    private String protagonista;
    private String antagonista;
    private String pelicula;

    public Prota(String protagonista, String antagonista, String pelicula) {
        this.protagonista = protagonista;
        this.antagonista = antagonista;
        this.pelicula = pelicula;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public String getAntagonista() {
        return antagonista;
    }

    public void setAntagonista(String antagonista) {
        this.antagonista = antagonista;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return "Prota [antagonista=" + antagonista + ", pelicula=" + pelicula + ", protagonista=" + protagonista + "]";
    }

}
