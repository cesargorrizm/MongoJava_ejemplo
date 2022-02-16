package com.becaries.MongoJava.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Villano {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Edad")
    @Expose
    private String edad;
    @SerializedName("Ciudad")
    @Expose
    private String ciudad;
    @SerializedName("Vehiculo")
    @Expose
    private String vehiculo;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Genero")
    @Expose
    private String genero;
    @SerializedName("Creacion")
    @Expose
    private String creacion;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("Universidad")
    @Expose
    private String universidad;
    @SerializedName("Titulacion")
    @Expose
    private String titulacion;


    
    public Villano(String id, String nombre, String edad, String ciudad, String vehiculo, String email, String genero,
            String creacion, String ipAddress, String universidad, String titulacion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.vehiculo = vehiculo;
        this.email = email;
        this.genero = genero;
        this.creacion = creacion;
        this.ipAddress = ipAddress;
        this.universidad = universidad;
        this.titulacion = titulacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    @Override
    public String toString() {
        return "Villano [ciudad=" + ciudad + ", creacion=" + creacion + ", edad=" + edad + ", email=" + email
                + ", genero=" + genero + ", id=" + id + ", ipAddress=" + ipAddress + ", nombre=" + nombre
                + ", titulacion=" + titulacion + ", universidad=" + universidad + ", vehiculo=" + vehiculo + "]";
    }

    

}