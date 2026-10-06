package com.campeonato.app.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "asociaciones")
public class Asociacion {
    public static final String SEQUENCE_NAME = "asociaciones_sequence";

    @Id
    private Long id;
    private String nombre;
    private String pais;
    private String presidente;

    public Asociacion() {}

    public Asociacion(Long id, String nombre, String pais, String presidente) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.presidente = presidente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }
}