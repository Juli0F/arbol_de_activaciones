/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.abs.instruccion;

import com.mycompany.arbol_activacion.abs.Arbol;
import com.mycompany.arbol_activacion.abs.Instruccion;
import com.mycompany.arbol_activacion.abs.Nodo;
import com.mycompany.arbol_activacion.abs.Simbolo;
import com.mycompany.arbol_activacion.abs.Tabla;

/**
 *
 * @author julio
 */
public class Identificador extends Instruccion {
    private String id;

    public Identificador(String id, int fila, int columna) {
        super(fila, columna);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol arbol) {
        if (tabla.existKey(id)) {
            Exception e = new Exception("Id Existe en el Entorno actual");
            arbol.getExcepciones().add(e);
            return false;
        }
        tabla.insertSimbol(new Simbolo(id,0,"variable",this.getFila(), this.getColumna()));
        return true;
    }

    @Override
    public Nodo getNodo(Arbol arbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
