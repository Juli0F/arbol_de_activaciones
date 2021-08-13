/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arbol_activacion.principal;

import com.mycompany.arbol_activacion.abs.Arbol;
import com.mycompany.arbol_activacion.abs.Instruccion;
import com.mycompany.arbol_activacion.abs.Nodo;
import com.mycompany.arbol_activacion.abs.Tabla;
import com.mycompany.arbol_activacion.abs.instruccion.CallFunction;
import com.mycompany.arbol_activacion.abs.instruccion.Function;
import com.mycompany.arbol_activacion.abs.instruccion.Programa;
import com.mycompany.arbol_activacion.analizador.Lexer;
import com.mycompany.arbol_activacion.analizador.parser;
import com.mycompany.arbol_activacion.write.WriteFile;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author julio
 */
public class Principal {

    public static String postFijo = "PostFijo: ";

    public double compilar(String cadena) {
        double contador = 0;
        try {
            System.out.println("Iniciando ...");
            Lexer lex = new Lexer(new StringReader(cadena));
            parser p = new parser(lex);

            List<Instruccion> instruccion = (List<Instruccion>) p.parse().value;

            Tabla tabla = new Tabla(null, "global");
            Arbol ast = new Arbol(instruccion, null, null);
            

            ast.getInstruccion().forEach(inst -> {

                if ((inst instanceof Programa) && contador == 0) {

                    Programa programa = (Programa) inst;

                    programa.getLst().forEach(fun -> {

                        if (fun instanceof Function function) {

                            ast.addFunction(function);
                        }

                    });

                    programa.getLstInstrucciones().forEach(fun -> {
                        fun.interpretar(tabla, ast);

                    });
                }
            });

            Nodo instNodo = new Nodo("Inicio: ");//"Resultado: "+resultado
            
            
            
            
            
            
            
            ast.getInstruccion().forEach(inst -> {
                System.out.println("lst get nodo");
                if ((inst instanceof Programa) && contador == 0) {
                    //contador = 1;
                    
                    Programa programa = (Programa) inst;

                    programa.getLstInstrucciones().forEach(fun -> {
                        System.out.println("Buscando llamadas a funciones");
                        if (fun instanceof CallFunction call) {
                            System.out.println("Se encontraron llamadas a funciones");
                            Nodo nodo = call.getNodo(ast);
                            if (nodo != null ) {
                                instNodo.addHijoNodo(nodo);
                            }
                        }

                    });

                    
                }
            });
            
            
            
            
            
            
            //init.addHijoNodo(instNodo);
            String grafo = ast.getDot(instNodo);
            WriteFile wf = new WriteFile();
            wf.writeFile("grafo.dot", grafo);
            wf.dibujar("grafo.dot", "grafo.png");
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
