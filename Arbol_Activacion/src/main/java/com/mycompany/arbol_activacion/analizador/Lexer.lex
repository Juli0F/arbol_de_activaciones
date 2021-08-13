package com.mycompany.arbol_activacion.analizador;


import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import java.io.Reader;


%%

%public
%class Lexer
%cup
%cupdebug
%unicode
%line
%column

%{
  StringBuilder string = new StringBuilder();
%}


LineTerminator 		 = \n|\r|\r\n
InputCharacter 		 = [^\r\n]
WhiteSpace              = {LineTerminator} | [ \t\f]

/* comments */
Comment 			 = {TraditionalComment} | {EndOfLineComment}
TraditionalComment   = "<-" [^*] ~"->" | "<-" "-"+ ">"
EndOfLineComment     = ">>" {InputCharacter}* {LineTerminator}?
//*/

//Primitivos

NUMERO  			 = [0-9]+("."[  |0-9]+)?
//CADENA  			 = (\\\"|[^\n\r\"]|\\{WHITE_SPACE_CHAR}+\\)*  //"

ID                               = [:jletter:][:jletterdigit:]*



%%


<YYINITIAL>{
        "integer"                   {return new Symbol(sym.INTEGER,yyline+1,yycolumn+1,yytext());}
        ";"                         {return new Symbol(sym.SEMICOLON,yyline+1,yycolumn+1,yytext());}
        ":"                         {return new Symbol(sym.DOT,yyline+1,yycolumn+1,yytext());}
        "("                         {return new Symbol(sym.PAR_A,yyline+1,yycolumn+1,yytext());}
        ")"                         {return new Symbol(sym.PAR_C,yyline+1,yycolumn+1,yytext());}
        "="                         {return new Symbol(sym.IGUAL,yyline+1,yycolumn+1,yytext());}
        "procedimiento"             {return new Symbol(sym.PROCEDIMIENTO,yyline+1,yycolumn+1,yytext());}
        "begin"                     {return new Symbol(sym.BEGIN,yyline+1,yycolumn+1,yytext());}
        "end"                       {return new Symbol(sym.END,yyline+1,yycolumn+1,yytext());}
        "programa"                  {return new Symbol(sym.PROGRAMA,yyline+1,yycolumn+1,yytext());}
        {NUMERO}                    {return new Symbol(sym.NUMERO,yyline+1, yycolumn+1,(yytext()));}
	{ID}                        {;return new Symbol(sym.ID,yyline+1, yycolumn+1,(yytext()));}
	
	
	{WhiteSpace}   {}
[^] {System.out.println("Error: "+ yytext()); }

<<EOF>>             { return new Symbol(sym.EOF); }
}



 
