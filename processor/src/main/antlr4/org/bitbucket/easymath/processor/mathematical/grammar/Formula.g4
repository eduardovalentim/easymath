grammar Formula;

// starting point for parsing a formula
formula
    :   expression
    ;

expression
    :   LPAREN expression RPAREN                                         #parenthesis
    |   LBRACK expression RBRACK                                         #brackets
    |   LBRACE expression RBRACE                                         #braces
    |   (operator=ADD|operator=SUB) expression                           #unary
    |   expression operator=BANG                                         #unary
    |   expression operator=CARET expression                             #binary
    |   expression (operator=MUL|operator=DIV|operator=MOD) expression   #binary
    |   expression (operator=ADD|operator=SUB) expression                #binary
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
