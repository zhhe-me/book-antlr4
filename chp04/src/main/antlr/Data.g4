grammar Data ;

file    : group+ ;

group   : INT sequence[$INT.int] ;

sequence[int n]
locals [int i=1;]
        : ( {$i<=$n}? INT {$i++;} )*    // match n integers
        ;

INT     : [0-9]+ ;              // match integers
WS      : [ \t\r\n]+ -> skip;   // toss out all whitespace