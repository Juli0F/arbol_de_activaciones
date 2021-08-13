/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.abs;

import com.mycompany.arbol_activacion.abs.instruccion.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author julio
 */
public class Arbol {

    private List<Instruccion> instruccion;
    private Tabla tabla;
    private List<Exception> excepciones;
    private String consola;
    private String dot;
    private int contador;
    //id->funcion, int -> cantidad parametros = posicion
    private HashMap<String, HashMap<Integer, Integer>> position;
    private HashMap<String, List<Function>> funciones;

    public Arbol(List<Instruccion> instruccion, Tabla tabla, List<Exception> excepciones) {
        this.instruccion = instruccion;
        this.tabla = tabla;
        this.excepciones = new ArrayList<>();
        this.consola = "";
        this.dot = "";
        this.contador = 0;
        this.position = new HashMap<>();
        this.funciones = new HashMap<>();
    }

    public boolean addFunction(Function function) {
        if (position.containsKey(function.getId())) {

            HashMap<Integer, Integer> parameterValue = position.get(function.getId());
            if (parameterValue.containsKey((function.getParameters()== null)?0:function.getParameters().size())) {
                System.out.println("Existe una funcion con las mismas caracteristicas");
                return false;
            }

            List<Function> lstFunction = this.funciones.get(function.getId());
            parameterValue.put(function.getParameters().size(), lstFunction.size());
            lstFunction.add(function);
            return true;
        }
        
        HashMap<Integer,Integer> nuevo = new HashMap<>();
        nuevo.put((function.getParameters() == null)?0:function.getParameters().size(), 0);
        position.put(function.getId(), nuevo);
        List<Function> lst = new ArrayList<>();
        lst.add(function);
        this.funciones.put(function.getId(), lst);
        return true;

    }

    public Object getFunction(String id, int cantParam) {
        if (position.containsKey(id)) {

            HashMap<Integer, Integer> parameterValue = position.get(id);
            if (parameterValue.containsKey(cantParam)) {
                int positionFunction = parameterValue.get(cantParam);
                return this.funciones.get(id).get(positionFunction);
                
            }

            return null;
        }
        return null;
    }

    public List<Instruccion> getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(List<Instruccion> instruccion) {
        this.instruccion = instruccion;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public List<Exception> getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(List<Exception> excepciones) {
        this.excepciones = excepciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public void update(String cadena) {
        this.consola += cadena + "\n";
    }

    public String getDot(Nodo raiz) {

        this.dot = "";
        this.dot += "digraph {\n";
        this.dot += "n0[label=\"" + raiz.getValor().replace("\"", "\\\"") + "\"];\n";
        this.contador = 1;
        this.recorrerAST("n0", raiz);
        this.dot += "}";
        return this.dot;
    }

    public void recorrerAST(String idPadre, Nodo nodoPadre) {
        nodoPadre.getHijos().forEach(hijo -> {
            String nombreHijo = "n" + String.valueOf(this.contador);
            this.dot += nombreHijo + "[label=\"" + hijo.getValor() + "\"]";
            this.dot += idPadre + "->" + nombreHijo + ";\n";
            this.contador += 1;
            this.recorrerAST(nombreHijo, hijo);

        });

    }

}
