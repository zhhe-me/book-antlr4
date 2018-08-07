grammar LabeledExpr;
import CommonLexerRules ;

/** The start rule; begin parsing here. */
prag:   stat+ ;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    ;

expr:   expr op=('*' | '/') expr    # MulDiv
    |   expr op=('+' | '-') expr    # AddSub
    |   INT                         # int
    |   ID                          # id
    |   '(' expr ')'                # parens
    ;

MUL :   '*' ;
DIV :   '/' ;
ADD :   '+' ;
SUB :   '-' ;
