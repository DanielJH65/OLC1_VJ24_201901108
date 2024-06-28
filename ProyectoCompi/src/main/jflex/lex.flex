package Analizador;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import Exceptions.Errores;

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
COMML  = "/*""*"*([^/*//]|[^/*]"/"|"*"[^//])*"*"*"*/"
BLANCO = [ \r\t\f\n]+
DIGITO = [0-9]+
DOUBLE = [0-9]+"."[0-9]+
ID     = [a-zA-Z][a-zA-Z0-9_]*
CADENA = [\"](\\\"|[^\"])*[\"]
CHAR = [\'](\\\'|\\\n|\\\\|\\\t|\\\r|\\\"|[^\'])?[\']

%{
    public LinkedList<Errores> errores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
%init}

%%

<YYINITIAL> "println"                                   {return new Symbol(sym.PRINTLN, yyline, yycolumn, yytext());}
<YYINITIAL> "true"                                      {return new Symbol(sym.TRUE, yyline, yycolumn, yytext());}
<YYINITIAL> "false"                                     {return new Symbol(sym.FALSE, yyline, yycolumn, yytext());}
<YYINITIAL> "int"                                       {return new Symbol(sym.RINT, yyline, yycolumn, yytext());}
<YYINITIAL> "double"                                    {return new Symbol(sym.RDOUBLE, yyline, yycolumn, yytext());}
<YYINITIAL> "bool"                                      {return new Symbol(sym.RBOOL, yyline, yycolumn, yytext());}
<YYINITIAL> "char"                                      {return new Symbol(sym.RCHAR, yyline, yycolumn, yytext());}
<YYINITIAL> "string"                                    {return new Symbol(sym.RSTRING, yyline, yycolumn, yytext());}
<YYINITIAL> "void"                                      {return new Symbol(sym.RVOID, yyline, yycolumn, yytext());}
<YYINITIAL> "var"                                       {return new Symbol(sym.RVAR, yyline, yycolumn, yytext());}
<YYINITIAL> "const"                                     {return new Symbol(sym.RCONST, yyline, yycolumn, yytext());}
<YYINITIAL> "if"                                        {return new Symbol(sym.RIF, yyline, yycolumn, yytext());}
<YYINITIAL> "else"                                      {return new Symbol(sym.RELSE, yyline, yycolumn, yytext());}
<YYINITIAL> "match"                                     {return new Symbol(sym.RMATCH, yyline, yycolumn, yytext());}
<YYINITIAL> "do"                                        {return new Symbol(sym.RDO, yyline, yycolumn, yytext());}
<YYINITIAL> "while"                                     {return new Symbol(sym.RWHILE, yyline, yycolumn, yytext());}
<YYINITIAL> "for"                                       {return new Symbol(sym.RFOR, yyline, yycolumn, yytext());}
<YYINITIAL> "continue"                                  {return new Symbol(sym.RCONTINUE, yyline, yycolumn, yytext());}
<YYINITIAL> "break"                                     {return new Symbol(sym.RBREAK, yyline, yycolumn, yytext());}
<YYINITIAL> "start_with"                                {return new Symbol(sym.RSTART, yyline, yycolumn, yytext());}
<YYINITIAL> "return"                                    {return new Symbol(sym.RRETURN, yyline, yycolumn, yytext());}

<YYINITIAL> ";"                                         {return new Symbol(sym.PYC, yyline, yycolumn, yytext());}
<YYINITIAL> "("                                         {return new Symbol(sym.PARA, yyline, yycolumn, yytext());}
<YYINITIAL> ")"                                         {return new Symbol(sym.PARC, yyline, yycolumn, yytext());}
<YYINITIAL> "+"                                         {return new Symbol(sym.ADD, yyline, yycolumn, yytext());}
<YYINITIAL> "-"                                         {return new Symbol(sym.SUB, yyline, yycolumn, yytext());}
<YYINITIAL> "*"                                         {return new Symbol(sym.MULTI, yyline, yycolumn, yytext());}
<YYINITIAL> "/"                                         {return new Symbol(sym.DIV, yyline, yycolumn, yytext());}
<YYINITIAL> "%"                                         {return new Symbol(sym.MODULE, yyline, yycolumn, yytext());}
<YYINITIAL> "=="                                        {return new Symbol(sym.EQUAL, yyline, yycolumn, yytext());}
<YYINITIAL> "!="                                        {return new Symbol(sym.DIFFERENT, yyline, yycolumn, yytext());}
<YYINITIAL> "!"                                         {return new Symbol(sym.NOT, yyline, yycolumn, yytext());}
<YYINITIAL> "<="                                        {return new Symbol(sym.SMALLEREQ, yyline, yycolumn, yytext());}
<YYINITIAL> "<"                                         {return new Symbol(sym.SMALLER, yyline, yycolumn, yytext());}
<YYINITIAL> ">="                                        {return new Symbol(sym.GREATEREQ, yyline, yycolumn, yytext());}
<YYINITIAL> ">"                                         {return new Symbol(sym.GREATER, yyline, yycolumn, yytext());}
<YYINITIAL> "||"                                        {return new Symbol(sym.OR, yyline, yycolumn, yytext());}
<YYINITIAL> "&&"                                        {return new Symbol(sym.AND, yyline, yycolumn, yytext());}
<YYINITIAL> "^"                                         {return new Symbol(sym.XOR, yyline, yycolumn, yytext());}
<YYINITIAL> ":"                                         {return new Symbol(sym.DOSP, yyline, yycolumn, yytext());}
<YYINITIAL> "="                                         {return new Symbol(sym.EQUALP, yyline, yycolumn, yytext());}
<YYINITIAL> "_"                                         {return new Symbol(sym.GUION, yyline, yycolumn, yytext());}
<YYINITIAL> "{"                                         {return new Symbol(sym.LLAVEA, yyline, yycolumn, yytext());}
<YYINITIAL> "}"                                         {return new Symbol(sym.LLAVEC, yyline, yycolumn, yytext());}
<YYINITIAL> ","                                         {return new Symbol(sym.COMA, yyline, yycolumn, yytext());}

<YYINITIAL> {DIGITO}                                    {return new Symbol(sym.DIGITO, yyline, yycolumn, yytext());}
<YYINITIAL> {DOUBLE}                                    {return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext());}
<YYINITIAL> {ID}                                        {return new Symbol(sym.ID, yyline, yycolumn, yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn, cadena);
}
<YYINITIAL> {CHAR} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    if(cadena.length() > 1) return new Symbol(sym.CHAR, yyline, yycolumn, cadena.charAt(1));
    return new Symbol(sym.CHAR, yyline, yycolumn, cadena.charAt(0));
}

<YYINITIAL> {BLANCO}                                    {}
<YYINITIAL> {COMUL}                                     {}
<YYINITIAL> {COMML}                                     {}

\n {yychar = 1;}

. {
    errores.add(new Errores("Lexico", "Este es un error lexico: "+yytext(), yyline, yycolumn));
}