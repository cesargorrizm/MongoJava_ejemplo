package com.becaries.MongoJava.modelos;

public class Usuario {
    private String id;
    private String user;
    private String passwword;
    private String rol;

    public Usuario(String id, String user, String passwword, String rol) {
        this.id = id;
        this.user = user;
        this.passwword = passwword;
        this.rol = rol;
    }
    public Usuario(String user, String passwword, String rol) {
        this.user = user;
        this.passwword = passwword;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswword() {
        return passwword;
    }

    public void setPasswword(String passwword) {
        this.passwword = passwword;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
