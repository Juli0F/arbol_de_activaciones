/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.abs;

/**
 *
 * @author julio
 */
public class Simbolo {
    private String id;
    private String categoria;
    private int fila;
    private int columna;
    private Object value;
    private boolean keep;

    public Simbolo(String id, Object value, String categoria, int fila, int columna) {
        this.id = id;
        this.value = value;
        this.categoria = categoria;
        this.fila = fila;
        this.columna = columna;
        this.keep = false;
    }

    public boolean isKeep() {
        return keep;
    }

    public void setKeep(boolean keep) {
        this.keep = keep;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    
    
}
