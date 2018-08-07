grammar Csv ;

file    :   hdr row+ ;
hdr     :   row ;
row     :   field (',' field)* '\r'? '\n' ;
field   :   TEXT
        |   STRING
        |
        ;

fragment
TEXT    :   ~[,\n\r\"]+ ;
STRING  :   '"' ('""'|~'"')* '"' ;
