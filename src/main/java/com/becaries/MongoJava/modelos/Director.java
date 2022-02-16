package com.becaries.MongoJava.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Director {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Edad")
    @Expose
    private int edad;
    @SerializedName("Ciudad_nacimiento")
    @Expose
    private String ciudadNacimiento;
    @SerializedName("Vehiculo")
    @Expose
    private String vehiculo;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Genero")
    @Expose
    private String genero;
    @SerializedName("Ip_movil")
    @Expose
    private String ipMovil;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("Empresa")
    @Expose
    private String empresa;
    @SerializedName("Titulacion")
    @Expose
    private String titulacion;
    public Director(String id, String director, int edad, String ciudadNacimiento, String vehiculo, String email,
            String genero, String ipMovil, String ipAddress, String empresa, String titulacion) {
        this.id = id;
        this.director = director;
        this.edad = edad;
        this.ciudadNacimiento = ciudadNacimiento;
        this.vehiculo = vehiculo;
        this.email = email;
        this.genero = genero;
        this.ipMovil = ipMovil;
        this.ipAddress = ipAddress;
        this.empresa = empresa;
        this.titulacion = titulacion;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
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
    public String getIpMovil() {
        return ipMovil;
    }
    public void setIpMovil(String ipMovil) {
        this.ipMovil = ipMovil;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getTitulacion() {
        return titulacion;
    }
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }
    @Override
    public String toString() {
        return "Director [ciudadNacimiento=" + ciudadNacimiento + ", director=" + director + ", edad=" + edad
                + ", email=" + email + ", empresa=" + empresa + ", genero=" + genero + ", id=" + id + ", ipAddress="
                + ipAddress + ", ipMovil=" + ipMovil + ", titulacion=" + titulacion + ", vehiculo=" + vehiculo + "]";
    }
   
    
   
}
