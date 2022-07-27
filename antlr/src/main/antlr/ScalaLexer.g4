lexer grammar ScalaLexer;

//lexer
fragment DIGIT : [0-9];
fragment DIGIT_NO_ZERO : [1-9];
fragment SYMBOL : ([a-z] | [A-Z]);
fragment UNDERLINE : '_';
COMMENT : ('/*' .*? '*/' | '//' ~[\r\n]*) -> skip;
WHITE_SPACE : [\u0020\u0009\u000C\u000A] -> skip;
NEW_LINE :[\u000A\u000D] -> skip;

INT : 'Int';
DOUBLE : 'Double';
BOOLEAN : 'Boolean';
CHAR : 'Char';
ARRAY : 'Array';
STRING_WORD : 'String';
UNIT : 'Unit';
TYPE : INT | DOUBLE | BOOLEAN | CHAR | STRING_WORD;

//Regular keywords
CASE : 'case';
DEF : 'def';
DO : 'do';
ELSE : 'else';
FALSE : 'false';
FOR : 'for';
IF : 'if';
NEW : 'new';
NULL : 'null';
OBJECT : 'object';
PACKAGE : 'package';
RETURN : 'return';
THEN : 'then';
TRUE : 'true';
VAL : 'val';
VAR : 'var';
WHILE : 'while';


ID : (UNDERLINE | SYMBOL)(SYMBOL | DIGIT | UNDERLINE)*;

PLUS : '+';
MINUS : '-';
ASSIGN : '=';
EQ : '==';
MULT : '*';
MOD : '%';
DIV :'/';
AND : '&&';
OR : '||';
MOVEMENT : '=>';
L_ARROW : '<';
R_ARROW : '>';
L_ARROW_EQ : '<=';
R_ARROW_EQ : '>=';
NOT_EQ : '!=';
NOT : '!';

//Parens
L_SQUARE : '[';
R_SQUARE : ']';
L_CURL : '{';
R_CURL : '}';
L_PAREN : '(';
R_PAREN : ')';

//Delims
DOT : '.';
COMMA : ',';
COLON : ':';
SEMICOLON : ';';

CHAR_ESCAPE_SEQ : '\\' ('b' | 't' | 'n' | 'f' | 'r' | '"' | '\'' | '\\');

//Literals
INTEGER_LITERAL : DIGIT | DIGIT_NO_ZERO DIGIT*;
BOOL_LITERAL : TRUE | FALSE;
CHAR_LITERAL : '\'' (. | CHAR_ESCAPE_SEQ) '\'' ;
STRING_LITERAL: '"' ~["\n\r]* '"';

