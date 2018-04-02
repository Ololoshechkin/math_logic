grammar Expression;

@header {
package antlr.generated;
import hw0.*;
}

expression returns [NodeWrapper node]
    : disj=disjunction {$node = $disj.node;}
    | disj1=disjunction IMPL exp1=expression {$node = new Implication($disj1.node, $exp1.node);}
    ;
disjunction returns [NodeWrapper node]
    : conj=conjunction {$node = $conj.node;}
    | disj1=disjunction OR conj1=conjunction {$node = new Disjunction($disj1.node, $conj1.node);}
    ;
conjunction returns [NodeWrapper node]
    : neg=negation {$node = $neg.node;}
    | conj1=conjunction AND neg1=negation {$node = new Conjunction($conj1.node, $neg1.node);}
    ;
negation returns [NodeWrapper node]
    : var=variable {$node = $var.node;}
    | '(' exp=expression ')' {$node = $exp.node;}
    | NOT neg=negation {$node = new Negation($neg.node);}
    ;

variable returns [NodeWrapper node] : VAR {$node = new Letter($VAR.text);};


IMPL : '->';
OR : '|';
AND : '&';
NOT : '!';
VAR : [A-Z]([A-Z0-9])*;

WS: [ \n\t\r]+ -> skip;