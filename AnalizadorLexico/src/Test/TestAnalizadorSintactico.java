package Test;

import Sintactico.AnalizadorSintactico;

public class TestAnalizadorSintactico {

    public static void main(String[] args) {
        AnalizadorSintactico app = new AnalizadorSintactico("src/Lectura/datos.txt");
        app.LlDriver();
    }
    
}
