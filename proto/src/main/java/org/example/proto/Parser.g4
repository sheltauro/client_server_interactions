grammar Parser;

program: messageBody EOF;

messageBody: MESSAGE messageRest;

messageRest: ID LCURLY field* RCURLY;

field: label type ID EQ INT SEMI;

label: OPTIONAL
     | REQUIRED
     | REPEATED
     ;

type: INT32
    | STRING
    | BOOL
    ;

MESSAGE: 'message';

OPTIONAL: 'optional';

REQUIRED: 'required';

REPEATED: 'repeated';

INT32: 'int32';

STRING: 'string';

BOOL: 'bool';

LCURLY: '{';

RCURLY: '}';

EQ: '=';

SEMI: ';';

ID: [a-zA-Z_][a-zA-Z_0-9]* ;

INT: [0-9]+;

LINE_COMMENT: '//' ~[\r\n]* -> skip;

SPACE: ' ' -> skip;

NEWLINE: '\n' -> skip;