grammar Formula;

// starting point for parsing a formula
formula
    :   expression
    ;

expression
    :   expression operator=BANG                                         #fatorial
    |   (operator=ADD|operator=SUB) expression                           #unary
    |   expression operator=CARET expression                             #exponentiation
    |   expression (operator=MUL|operator=DIV|operator=MOD) expression   #primary
    |   expression (operator=ADD|operator=SUB) expression                #secondary
    |   LPAREN expression RPAREN                                         #tertiary
    |   Number                                                           #constant
    |   Identifier                                                       #input
    ;

Identifier
    :   Letter+
    ;

Number
    :   Digit (DOT Digit)*
    ;
    
fragment
Letter
    :   [a-zA-Z_]
    ;

fragment
Digit
    :   '0'..'9'
    ;

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';

ASSIGN          : '=';
GT              : '>';
LT              : '<';
BANG            : '!';
TILDE           : '~';
QUESTION        : '?';
COLON           : ':';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : '&&';
OR              : '||';
INC             : '++';
DEC             : '--';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
BITAND          : '&';
BITOR           : '|';
CARET           : '^';
MOD             : '%';
//
// Whitespace
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;
