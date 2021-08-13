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
public class Function extends Instruccion {

    private String id;
    private List<String> parameters;
    private List<Instruccion> instrucciones;
    private String parametros;

    public Function(String id, List<String> parameters, List<Instruccion> instrucciones, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.parameters = parameters;
        this.instrucciones = instrucciones;
        this.parametros = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol arbol) {
        Tabla nueva = new Tabla(tabla, "Function " + id);
        if (instrucciones != null && instrucciones.size() > 0) {
            boolean err = false;

            instrucciones.forEach(x -> {
                System.out.println("Interpretando: " + x.getClass().getSimpleName());
                System.out.println("->" + x.getClass().getTypeName());
                if (x instanceof Programa) {
                    System.out.println("Program dentro de una funcion");
                } else {

                    x.interpretar(nueva, arbol);
                }
            });

            return true;
        }
        return false;
    }

    @Override
    public Nodo getNodo(Arbol arbol) {
        Nodo nodo = new Nodo("Funcion ");

        this.parametros = id + "( ";
        if (this.parameters != null) {
            this.parameters.forEach(x -> {
                parametros += x + " ";
            });
        }
        this.parametros += " )";

        nodo.addHijo(parametros);

        if (instrucciones != null) {
            this.instrucciones.forEach(callFUnction -> {
                if (callFUnction instanceof CallFunction call) {
                    Nodo callNodo = call.getNodo(arbol);

                    if (callNodo != null) {
                        nodo.addHijoNodo(callNodo);
                    }

                }
            });
        }
        return nodo;
    }

}
