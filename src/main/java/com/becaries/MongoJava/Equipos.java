package com.becaries.MongoJava;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class Equipos {
    private String nombre;
    private String presidente;
    private String id;
    public Equipos(){}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Equipos(String nombre, String presidente,String id) {
        setNombre(nombre);
        setPresidente(presidente);
        setId(id);
        
    }
    // Transformo un objecto que me da MongoDB a un Objecto Java
	public Equipos(BasicDBObject dBObjectEquipo) {
		this.nombre = dBObjectEquipo.getString("nombre");
        this.presidente = dBObjectEquipo.getString("presidente");
        this.id = dBObjectEquipo.getString("id");
		// Cuidado cuando trabajamos con Arrays o Listas
		

	}

	public BasicDBObject toDBObjectEquipo() {

		// Creamos una instancia BasicDBObject
		BasicDBObject dBObjectEquipo = new BasicDBObject();

		dBObjectEquipo.append("nombre", this.getNombre());
        dBObjectEquipo.append("presidente", this.getPresidente());
        dBObjectEquipo.append("id", this.getId());


		return dBObjectEquipo;
	}
    public String getPresidente() {
        return presidente;
    }
    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
