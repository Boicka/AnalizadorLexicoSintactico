package Tablas;

public class Tablas {

    String[] iniciales = {"programa", "lista_sent", "sent_final", "sentencia", 
        "lista_id", "id_final", "lista_expr", "lista_exprfinal", "expresion",
        "expr_arit", "operador", "inicio"};

    String[] finales = {"class", "id", "{", "}", "id", "read", ";", "=",
        "write", "enteros", "reales", "+", "-", "*"};
    
    String[] ladoDerecho = {"",    "class id { lista_sent}",
        "sentencia sent_final", "sentencia sent_final",  "",
        "tipo lista_id:",   "id = espresion ;", "read ( lista_id ) ;",
        "write ( lista_expr) ;",    "id id_final",
        ", id_final",   "",    "expresion lista_exprfinal",
        ", expresion lista_exprfinal",  "",    "expr_arit lista_exprfinal",
        "operador expr_arit expr_final",    "",    "( expresion )",
        "id","enteros", "reales",   "+",    "-",    "*",    "programa" };
}
