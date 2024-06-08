package Analizador;

import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%

%class lexico
%public
%line
%char
%column
%cup
%full
%ignorecase
//%debug

COMUL  = ("//".*\r\n)|("//".*\n)|("//".*\r)
COMML  = "<!""!"*([^!>]|[^!]">"|"!"[^>])*"!"*"!>"
BLANCO = [ \r\t]+
DIGITO = [0-9]+
DOUBLE = [0-9]+"."[0-9]+
ID     = [a-zA-Z][a-zA-Z0-9]*
CADENA = [\"](\\\"|[^\"])*[\"]

%{
    public ArrayList<String> errores = new ArrayList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

%%

<YYINITIAL> "println"                                   {return new Symbol(sym.PRINTLN, yyline, yycolumn, yytext());}

<YYINITIAL> ";"                                         {return new Symbol(sym.PYC, yyline, yycolumn, yytext());}
<YYINITIAL> "("                                         {return new Symbol(sym.PARA, yyline, yycolumn, yytext());}
<YYINITIAL> ")"                                         {return new Symbol(sym.PARC, yyline, yycolumn, yytext());}

<YYINITIAL> {DIGITO}                                    {return new Symbol(sym.DIGITO, yyline, yycolumn, yytext());}
<YYINITIAL> {DOUBLE}                                    {return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn, cadena);
}

<YYINITIAL> {BLANCO}                                    {}
<YYINITIAL> {COMUL}                                     {}
<YYINITIAL> {COMML}                                     {}

\n {yychar = 1;}

. {
    System.err.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yycolumn);
}