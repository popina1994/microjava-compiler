package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import org.apache.log4j.*;

%%

%{
    boolean noError = true;
    int lineNum = -1;

    Logger log = Logger.getLogger(getClass());

    private Symbol new_symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn);
    }

    private Symbol new_symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn, value);
    }

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
    return new_symbol(sym.EOF);
%eofval}

%%

" "    {}
"\b"   {}
"\t"   {}
"\r\n" {}
"\f"   {}

"program" { return new_symbol(sym.PROGRAM, yytext()); }
"static" { return new_symbol(sym.STATIC, yytext()); }
"break" { return new_symbol(sym.BREAK, yytext()); }
"class" { return new_symbol(sym.CLASS, yytext()); }
"else" { return new_symbol(sym.ELSE, yytext()); }
"const" { return new_symbol(sym.CONST, yytext()); }
"if" { return new_symbol(sym.IF, yytext()); }
"new" { return new_symbol(sym.NEW, yytext()); }
"print" { return new_symbol(sym.PRINT, yytext()); }
"read" { return new_symbol(sym.READ, yytext()); }
"return" { return new_symbol(sym.RETURN, yytext()); }
"void" { return new_symbol(sym.VOID, yytext()); }
"for" { return new_symbol(sym.FOR, yytext()); }
"extends" { return new_symbol(sym.EXTENDS, yytext()); }
"continue" { return new_symbol(sym.CONTINUE, yytext()); }

"+" { return new_symbol(sym.OP_ADD, yytext()); }
"-" { return new_symbol(sym.OP_SUB, yytext()); }
"*" { return new_symbol(sym.OP_MUL, yytext()); }
"/" { return new_symbol(sym.OP_DIV, yytext()); }
"%" { return new_symbol(sym.OP_MOD, yytext()); }

"==" { return new_symbol(sym.OP_EQ, yytext()); }
"!=" { return new_symbol(sym.OP_NOT_EQ, yytext()); }

">" { return new_symbol(sym.OP_GREAT, yytext()); }
"<" { return new_symbol(sym.OP_LESS, yytext()); }
">=" { return new_symbol(sym.OP_GTE, yytext()); }
"<=" { return new_symbol(sym.OP_LTE, yytext()); }

"&&" { return new_symbol(sym.OP_AND, yytext()); }
"||" { return new_symbol(sym.OP_OR, yytext()); }
"=" { return new_symbol(sym.OP_ASSIGN, yytext()); }
"+=" { return new_symbol(sym.OP_ASSIGN_ADD, yytext()); }
"-=" { return new_symbol(sym.OP_ASSIGN_SUB, yytext()); }
"*=" { return new_symbol(sym.OP_ASSIGN_MUL, yytext()); }
"/=" { return new_symbol(sym.OP_ASSIGN_DIV, yytext()); }
"%=" { return new_symbol(sym.OP_ASSIGN_MOD, yytext()); }
"++" { return new_symbol(sym.OP_INC, yytext()); }
"--" { return new_symbol(sym.OP_DEC, yytext()); }

";" { return new_symbol(sym.SEMI_COLUMN, yytext()); }
"," { return new_symbol(sym.COMMA, yytext()); }
"." { return new_symbol(sym.DOT, yytext()); }

"(" { return new_symbol(sym.PAR_LEFT, yytext()); }
")" { return new_symbol(sym.PAR_RIGHT	, yytext()); }
"[" { return new_symbol(sym.BRACKET_LEFT, yytext()); }
"]" { return new_symbol(sym.BRACKET_RIGHT, yytext()); }
"{" { return new_symbol(sym.BRACE_LEFT, yytext()); }
"}" { return new_symbol(sym.BRACE_RIGHT, yytext()); }

("false"|"true") { return new_symbol(sym.CONST_BOOL, yytext()); }


"//" { yybegin(COMMENT);}
<COMMENT> . { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

(\'[\x9 -~]\') {return new_symbol (sym.CONST_CHAR, yytext());}
[0-9]+ { return new_symbol(sym.CONST_NUM, new Integer(yytext())); }
([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]|_)* {return new_symbol (sym.IDENT, yytext());}

. { log.error("Leksicka greska ("+yytext()+") u liniji "+ (yyline + 1) + " i koloni: " + yycolumn);
noError = false; }
