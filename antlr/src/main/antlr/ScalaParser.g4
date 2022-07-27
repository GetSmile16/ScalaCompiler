parser grammar ScalaParser;

options {tokenVocab = ScalaLexer; }

//parser
program : statement* ;

statement : initArray | initVariable | functionDefinition | functionCall;// objectDefinition;

body : action*;

action : initArray | initVariable | assignVariable | ret | functionCall | assignArray | ifElse | cycleWhile;

//objectDefinition : OBJECT name L_CURL program R_CURL;
functionDefinition : DEF name L_PAREN parameters R_PAREN COLON (type | UNIT) ASSIGN L_CURL body R_CURL;
//returnVariant : COLON dataTypeFunction ASSIGN;
parameters : (name COLON (type | arrayType) COMMA?)*;
name : ID;

functionCall : name L_PAREN arguments R_PAREN SEMICOLON?;
arguments : (exp COMMA?)*;

ret : RETURN exp SEMICOLON?; //| exp;

initVariable : VAR name (COLON type)? ASSIGN exp;
assignVariable : name ASSIGN exp SEMICOLON?;

initArray : VAR name (COLON arrayType)? ASSIGN arrayLiteral;
assignArray : dereferenceArray ASSIGN exp;
dereferenceArray : name L_PAREN exp R_PAREN SEMICOLON?; // old - name (L_PAREN exp R_PAREN)*
arrayLiteral : ARRAY L_PAREN (exp COMMA?)* R_PAREN SEMICOLON?; // old - ARRAY L_PAREN ((exp COMMA?)* | (arrayLiteral COMMA?)*) R_PAREN

ifElse : ifExp ifBody elseIf* else_?;
ifExp : IF L_PAREN exp R_PAREN;
ifBody : L_CURL body R_CURL;
elseIf : ELSE ifExp ifBody;
else_ : ELSE ifBody;
cycleWhile : WHILE L_PAREN exp R_PAREN L_CURL body R_CURL;

exp
 : (INTEGER_LITERAL | CHAR_LITERAL | BOOL_LITERAL | STRING_LITERAL) SEMICOLON? # LiteralExp
 | exp op = (MULT | DIV) exp # MathExp
 | exp op = (PLUS | MINUS) exp # MathExp
 | exp op = (L_ARROW_EQ | R_ARROW_EQ | L_ARROW | R_ARROW | EQ | NOT_EQ) exp # LogicExp
 | name # IdExp
 | functionCall # CallExp
 | dereferenceArray # IndexArrExp
 | NOT exp # NotExp
 ;

arrayType : ARRAY L_SQUARE type R_SQUARE;
type : INT | BOOLEAN | CHAR | STRING_WORD;