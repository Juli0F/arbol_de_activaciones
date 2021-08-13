/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.abs.instruccion;

import com.mycompany.arbol_activacion.abs.Arbol;
import com.mycompany.arbol_activacion.abs.Instruccion;
import com.mycompany.arbol_activacion.abs.Nodo;
import com.mycompany.arbol_activacion.abs.Tabla;
import java.util.List;

/**
 *
 * @author julio
 */
public class Programa extends Instruccion {

    private String id;
    private List<Instruccion> lstInstrucciones;
    private List<Instruccion> lst;

    public Programa(String id, List<Instruccion> lstInstrucciones, List<Instruccion> lst, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.lstInstrucciones = lstInstrucciones;
        this.lst = lst;
    }

    public List<Instruccion> getLst() {
        return lst;
    }

    public void setLst(List<Instruccion> lst) {
        this.lst = lst;
    }
    

    @Override
    public Object interpretar(Tabla tabla, Arbol arbol) {

        if (lstInstrucciones != null && lstInstrucciones.size() > 0) {

            lstInstrucciones.forEach(x -> {

                x.interpretar(tabla, arbol);
            });
            return false;
        }
        return true;
    }

    @Override
    public Nodo getNodo(Arbol arbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Instruccion> getLstInstrucciones() {
        return lstInstrucciones;
    }

    public void setLstInstrucciones(List<Instruccion> lstInstrucciones) {
        this.lstInstrucciones = lstInstrucciones;
    }

}
