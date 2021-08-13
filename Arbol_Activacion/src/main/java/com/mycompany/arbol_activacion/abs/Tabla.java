/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.abs;

import java.util.HashMap;

/**
 *
 * @author julio
 */
public class Tabla {
   private Tabla tablaActual;
   private  HashMap<String, Object> tabla;
   private  Tabla anterior;
   private String entorno;

    public Tabla(Tabla anterior, String entorno) {
        
        this.tabla = new HashMap<>();
        this.anterior = anterior;
        this.entorno = entorno;
        this.tablaActual = this;
    }
    public Object insertSimbol(Simbolo simbolo){
        
        if (tabla.containsKey(simbolo.getId())) {
            return new Exception("Error Semantico");
        }else {
            this.tabla.put(simbolo.getId(), simbolo);
            return true;
        }
        
    }
    public Object insertSimbol(String id,Simbolo simbolo){
        
        if (tabla.containsKey(simbolo.getId())) {
            return new Exception("Error Semantico");//GrammarException("Semantico", "identificador ya se encuentra registrado '"+simbolo.getId()+"'", simbolo.getFila(), simbolo.getColumna());
        }else {
            this.tabla.put(simbolo.getId(), simbolo);
            return true;
        }
        
    }
    /**
     * Busca el simbolo por id
     * si no lo encuentra lanza null, 
     * el encargado de lanzar la excepcion sera el metodo que llame
     * al id
     * @param id
     * @return  objeto de la clase simbolo
     */
    public Object getValue(String id){
        Tabla tbl = this.tablaActual;
        
        while(tbl != null){
            if (tbl.getTabla().containsKey(id)) {
                return tbl.tabla.get(id);
            }else{
                tbl = tbl.getAnterior();
            }
        }
        
        return null;
        
    }/**
     * Busca el simbolo solo en la tabla actual
     * @param id
     * @return instancia de la clase simbolo/**
     * Busca el simbolo solo en la tabla actual
     * @param id
     * @return instancia de la clase simbolo
     */
    public Object getSimboloTablaActual(String id){
        if (tablaActual.getTabla().containsKey(id)) {
            return tablaActual.getTabla().get(id);            
        }
        return null;
    }
    /**
     * aca debo verificar que los tipos se puedan castear automaticamente
     * por ejemplo si es un double y viene un int deberia pasar el int a double
     * @param simbolo
     * @return 
     */
    public Object actualizarTabla(Simbolo simbolo){
        Tabla tableActual = tablaActual;
        while(tableActual != null){
            if (tableActual.getTabla().containsKey(simbolo.getId())) {
                Simbolo symStorage = (Simbolo)tableActual.getTabla().get(simbolo.getId());
                
                
                    symStorage.setValue( simbolo.getValue());
                    return true;
                
                
            }
        }
        return false;
    }
    public Tabla(HashMap<String, Object> tabla) {
        this.tabla = tabla;
    }

    private HashMap<String, Object> getTabla() {
        return tabla;
    }

    public void setTabla(HashMap<String, Object> tabla) {
        this.tabla = tabla;
    }
    public boolean existKey(String key){
        return tabla.containsKey(key);
    }

    public Tabla getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(Tabla tablaActual) {
        this.tablaActual = tablaActual;
    }

    public Tabla getAnterior() {
        return anterior;
    }

    public void setAnterior(Tabla anterior) {
        this.anterior = anterior;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }
    
   
}
