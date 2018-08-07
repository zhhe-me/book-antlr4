grammar Expr;
import CommonLexerRules ;

/**
 supported input like below:
    193
    a = 5
    b = 6
    a
    a+b*2
    (1+2)*3
 */

/** The start rule; begin parsing here. */
prag:   stat+ ;

stat:   expr NEWLINE
    |   ID '=' expr NEWLINE
    |   NEWLINE
    ;

expr:   expr ('*' | '/') expr
    |   expr ('+' | '-') expr
    |   INT
    |   ID
    |   '(' expr ')'
    ;

