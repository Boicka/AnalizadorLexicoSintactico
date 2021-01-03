package Tablas;

public class Tablas {

    String[] noTerminales = {"programa", "lista_sent", "sent_final", "sentencia",
        "lista_id", "id_final", "lista_expr", "lista_exprfinal", "expresion", "expr_final",
        "expr_arit", "tipo", "operador", "inicio", "#"};

    String[] terminales = {"class", "id", "{", "}", ";", "=", "read", "(",
        ")", "write", ",", "enteros", "reales", "int", "float", "+", "-", "*"};

    String[] ladoDerecho = {"", "class id { lista_sent } #",
        "sentencia sent_final", "sentencia sent_final", "",
        "tipo lista_id ;", "id = expresion ;", "read ( lista_id ) ;",
        "write ( lista_expr ) ;", "id id_final",
        ", id id_final", "", "expresion lista_exprfinal",
        ", expresion lista_exprfinal", "", "expr_arit expr_final",
        "operador expr_arit expr_final", "", "( expresion )",
        "id", "enteros", "reales", "int", "float", "+", "-", "*", "programa"};

    int[][] matrizPredictiva = {
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 2, 2, 0, 0, 0},
        {0, 3, 0, 4, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 3, 0, 0, 0},
        {0, 6, 0, 0, 0, 0, 7, 0, 0, 8, 0, 0, 0, 5, 5, 0, 0, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 11, 0, 0, 0, 11, 0, 10, 0, 0, 0, 0, 0, 0, 0},
        {0, 12, 0, 0, 0, 0, 0, 12, 0, 0, 0, 12, 12, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 13, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 15, 0, 0, 0, 0, 0, 15, 0, 0, 0, 15, 15, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 17, 0, 0, 0, 17, 0, 0, 0, 0, 0, 0, 16, 16, 16},
        {0, 19, 0, 0, 0, 0, 0, 18, 0, 0, 0, 20, 21, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22, 23, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 25, 26},
        {27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public String[] getNoTerminales() {
        return noTerminales;
    }

    public String[] getTerminales() {
        return terminales;
    }

    public String[] getLadoDerecho() {
        return ladoDerecho;
    }

    public int[][] getMatrizPredictiva() {
        return matrizPredictiva;
    }

}
