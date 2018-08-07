grammar Log ;

file    :   row+ ;
row     :   ip STRING INT NL ;
ip      :   INT '.' INT '.' INT '.' INT ;

// row     :   IP STRING INT NL ;
//fragment
//IP      :   INT '.' INT '.' INT '.' INT ;

fragment
STRING  :   '"' .*? '"' ;

fragment
NL      :   '\n' ;

fragment
INT     :   [0-9]+ ;

WS      :   [ \t]   -> skip ;
