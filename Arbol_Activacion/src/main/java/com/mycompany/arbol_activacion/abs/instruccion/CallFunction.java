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
import java.util.function.Consumer;

/**
 *
 * @author julio
 */
public class CallFunction extends Instruccion {

    private String id;
    private List<Integer> lst;
    private String parametros;

    public CallFunction(String id, List<Integer> lst, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.lst = lst;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getLst() {
        return lst;
    }

    public void setLst(List<Integer> lst) {
        this.lst = lst;
    }

    @Override
    public Object interpretar(Tabla tabla, Arbol arbol) {
        Function functionResult = (Function) arbol.getFunction(id, (lst == null) ? 0 : lst.size());
        if (functionResult == null) {
            arbol.getExcepciones().add(new Exception("No Existe Funcion"));
            return false;
        }

        Tabla nueva = new Tabla(tabla, "LLamada");
        functionResult.interpretar(nueva, arbol);

        return true;
    }

    @Override
    public Nodo getNodo(Arbol arbol) {

        Function funcion = (Function) arbol.getFunction(id, (lst == null) ? 0 : lst.size());
        this.parametros = this.id+" ( ";

        if (funcion != null) {

            funcion.getParameters().forEach(p -> {
                parametros += p;

            });
            
            Nodo nodo = new Nodo(parametros+" )");
            nodo.addHijoNodo(funcion.getNodo(arbol));
            return nodo;
        }
        
        return null;
    }

}
