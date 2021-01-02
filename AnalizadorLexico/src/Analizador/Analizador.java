/*
Analizador.java
Instituto Tecnológico de León
Ingenieria en sistemas computacionales
Lenguajes y autómatas 1
Lunes y Miercoles 8:45 - 10:25
Viernes 8:45 - 9:35
Alumnos: Héctor Medel Negrete
         Juan de Dios Yebra Navarro
Version 1.0
 */
package Analizador;

import Escribir.Escritor;
import Lectura.Lector;
import java.util.Arrays;

public class Analizador {

    Token unidad;
    Lector lector;
    boolean pausa;
    String linea, programa;
    int apuntadorA, apuntadorB, estado, estatus;
    boolean seguirAnalizando;
    String[] errores;
    Token[] unidadesLexicas;
    Token[] simbolos;
    String[] palabrasReservadas = {"class", "float", "int", "read", "write"};

    public Analizador(String ruta) {
        lector = new Lector(ruta);
        apuntadorA = 0;//indice inicial
        apuntadorB = 0;//indice final
        seguirAnalizando = true;
        estado = 0;
        errores = new String[0];
        unidadesLexicas = new Token[0];
        simbolos = new Token[0];
        unidad = new Token();
        estatus = 1; //Cero es en medio de una linea
        //Uno final de la linea
        pausa = true;
    }

    public void AnalizadorLexico() {
        linea = lector.leer();
        if (linea != null) {
            linea.trim();
            apuntadorA = 0;//indice inicial
            apuntadorB = 0;//indice final
            Analiza();
        } else {
//            System.out.println("Aqui se termina el archivo");
//            System.out.println("si entro aqui");
            mostrarResultados();
            System.exit(0);
        }

    }

    public Token darToken() {
//        System.out.println(estatus);
//        System.out.println(lector.leyendo());
//        System.out.println(lector.obtenerLinea() +" " + lector.numeroLineas());
        if (lector.leyendo() && (lector.obtenerLinea() < lector.numeroLineas())) {
            if (estatus == 1) {
                pausa = true;
                linea = lector.leer();
                if (linea != null) {
                    linea.trim();
                    apuntadorA = 0;//indice inicial
                    apuntadorB = 0;//indice final
//                    System.out.println("Si entra");
                    Analiza();
                }
            } else if (estatus == 0) {
                pausa = true;
                Analiza();
            }
        } else {
            unidad = null;
        }
//        System.out.println(unidad);
        return unidad;
    }

    private void Analiza() {
        while (pausa == true) {

            if (apuntadorA != apuntadorB) {

                switch (estado) {
                    case 0:
                        if (isDigit(linea.charAt(apuntadorB))) {
                            estado = 1;
                            apuntadorB++;
                        } else {
                            estado = 6;
                        }
                        break;
                    case 1:
                        if (apuntadorB >= linea.length()) {
                            apuntadorB--;
                            estado = 2;
                        } else if (isDigit(linea.charAt(apuntadorB))) {
                            estado = 1;
                            apuntadorB++;
                        } else if (linea.charAt(apuntadorB) == '.') {
                            estado = 3;
                            apuntadorB++;
                        } else if (isEspecial(linea.charAt(apuntadorB)) || linea.charAt(apuntadorB) == ' ') {
                            apuntadorB--;
                            estado = 2;
                        } else {
                            generaError();
                        }
                        break;
                    case 2:
                        generaToken(201);
                        break;
                    case 3:
                        if (apuntadorB >= linea.length()) {
                            apuntadorB--;
                            generaError();
                        } else if (isDigit(linea.charAt(apuntadorB))) {
                            apuntadorB++;
                            estado = 4;
                        } else {
                            generaError();
                        }
                        break;
                    case 4:
                        if (apuntadorB >= linea.length()) {
                            apuntadorB--;
                            estado = 5;
                        } else if (isDigit(linea.charAt(apuntadorB))) {
                            estado = 4;
                            apuntadorB++;
                        } else if (isEspecial(linea.charAt(apuntadorB)) || linea.charAt(apuntadorB) == ' ') {
                            apuntadorB--;
                            estado = 5;
                        } else {
                            generaError();
                        }
                        break;
                    case 5:
                        generaToken(202);
                        break;
                    case 6:
                        if (isAlpha(linea.charAt(apuntadorB))) {
                            estado = 7;
                            apuntadorB++;
                        } else if (isMin(linea.charAt(apuntadorB))) {
                            estado = 9;
                            apuntadorB++;
                        } else if (isEspecial(linea.charAt(apuntadorB))) {
                            estado = 11;
                        } else {
                            generaError();
                        }
                        break;
                    case 7:
                        if (apuntadorB >= linea.length()) {
                            apuntadorB--;
                            estado = 8;
                        } else if (isDigit(linea.charAt(apuntadorB)) || isMin(linea.charAt(apuntadorB)) || linea.charAt(apuntadorB) == '_') {
                            estado = 7;
                            apuntadorB++;
                        } else if (linea.charAt(apuntadorB) == ' ' || isEspecial(linea.charAt(apuntadorB))) {
                            apuntadorB--;
                            estado = 8;
                        }
                        break;
                    case 8:
                        if (isDigit(linea.charAt(apuntadorB)) || isMin(linea.charAt(apuntadorB))) {
                            generaToken(200);
                        } else {
                            generaError();
                        }
                        break;
                    case 9://System.out.println("si entra aqui");
                        if (apuntadorB >= linea.length()) {
                            apuntadorB--;
                            estado = 10;
                        } else if (isMin(linea.charAt(apuntadorB))) {

                            estado = 9;
                            apuntadorB++;
                        } else if (linea.charAt(apuntadorB) == ' ' || isEspecial(linea.charAt(apuntadorB))) {
                            apuntadorB--;
                            estado = 10;
                        } else {
                            generaError();
                        }
                        break;
                    case 10:
                        generaToken(204);
                        break;
                    case 11:
                        generaToken(203);
                        break;
                }
            } else {//System.out.println("buscando otro token");
                if (linea.charAt(apuntadorA) == ' ') {
                    apuntadorA++;
                    apuntadorB++;

                } else {
                    switch (estado) {
                        case 0:
                            if (isDigit(linea.charAt(apuntadorB))) {
                                if (apuntadorB + 1 >= linea.length()) {
                                    estado = 2;
                                } else if (linea.charAt(apuntadorB + 1) == ' ' || isEspecial(linea.charAt(apuntadorB + 1))) {
                                    estado = 2;
                                } else {
                                    estado = 1;
                                    apuntadorB++;
                                }
                            } else {

                                estado = 6;
                            }
                            break;
                        case 2:
                            generaToken(201);
                            break;
                        case 6:
                            if (isAlpha(linea.charAt(apuntadorB))) {
//                                System.out.println("aqui ando");
                                estado = 7;
                                apuntadorB++;
                            } else if (isMin(linea.charAt(apuntadorB))) {

                                if (apuntadorB + 1 >= linea.length()) {
                                    estado = 10;
                                } else if (linea.charAt(apuntadorB + 1) == ' ' || isEspecial(linea.charAt(apuntadorB + 1))) {

                                    estado = 10;
                                } else {
                                    estado = 9;
                                    apuntadorB++;
                                }
                            } else if (isEspecial(linea.charAt(apuntadorB))) {

                                estado = 11;
                            } else {

                                generaError();
                            }
                            break;
                        case 10:
                            generaToken(204);
                            break;
                        case 11:
                            generaToken(203);
                            break;
                    }
                }

            }
        }
    }

    //Para mi es numero
    public boolean isDigit(char letra) {
        return Character.isDigit(letra);
    }

    public boolean isAlpha(char letra) {
        return Character.isUpperCase(letra);
    }

    public boolean isMin(char letra) {
        return Character.isLowerCase(letra);
    }

    public boolean isEspecial(char letra) {
        boolean respuesta = false;
        if (letra == '{' || letra == '}' || letra == ';' || letra == '=' || letra == '+' || letra == '*' || letra == '-' || letra == '(' || letra == ')' || letra == ',') {
            respuesta = true;
        }
        return respuesta;
    }

    public void generaToken(int num) {
        switch (num) {
            case 200:
                unidad = new Token(obtenerLexema(), num, "id");
                if (Arrays.asList(unidadesLexicas).isEmpty()) {
                    unidadesLexicas = new Token[1];
                    unidadesLexicas[0] = new Token(obtenerLexema(), num, "Identificador");
                } else {
                    if (siEsta(obtenerLexema()) == false) {
                        unidadesLexicas = agregarToken(obtenerLexema(), num, "Identificador");
                    }
                }
                if (Arrays.asList(simbolos).isEmpty()) {
                    simbolos = new Token[1];
                    simbolos[0] = new Token(obtenerLexema(), num, "Identificador");
                } else {
                    String lexema = obtenerLexema();
                    if (siEsta(lexema) == false) {
                        simbolos = agregarSimbolo(lexema, num, "Identificador");
                    }
                }
                break;
            case 201:
                unidad = new Token(obtenerLexema(), num, "int");
                break;
            case 202:
                unidad = new Token(obtenerLexema(), num, "float");
                break;
            case 203:
                unidad = new Token(obtenerLexema(), num, "Carácter simple");
                break;
            case 204:
                if (Arrays.asList(unidadesLexicas).isEmpty()) {
                    unidadesLexicas = new Token[1];
                    unidadesLexicas[0] = new Token(obtenerLexema(), num, "Palabra reservada");
                    unidad = new Token(obtenerLexema(), num, "Palabra reservada");
                } else {
                    unidadesLexicas = agregarToken(obtenerLexema(), num, "Palabra reservada");
                    unidad = new Token(obtenerLexema(), num, "Palabra reservada");
                }
                break;
        }
        apuntadorB++;
        apuntadorA = apuntadorB;
//        System.out.println("se crea un token");
//        System.out.println(unidad.atributo);
        if (apuntadorB >= linea.length()) {
            apuntadorB = linea.length() - 1;
            apuntadorA = linea.length() - 1;
            estado = 0;
            pausa = false;
//            AnalizadorLexico();
            estatus = 1;
        } else {
            estatus = 0;
            estado = 0;
            pausa = false;
//            Analiza();
        }
    }

    private Token[] agregarToken(String lexema, int atributo, String clasificacion) {
        Token unidad = new Token(lexema, atributo, clasificacion);
//        System.out.println(unidad);
        Token[] nuevo = new Token[unidadesLexicas.length + 1];
        System.arraycopy(unidadesLexicas, 0, nuevo, 0, unidadesLexicas.length);
        nuevo[unidadesLexicas.length] = unidad;
        return nuevo;
    }

    private Token[] agregarSimbolo(String lexema, int atributo, String clasificacion) {
        Token unidad = new Token(lexema, atributo, clasificacion);

        Token[] nuevo = new Token[simbolos.length + 1];
        System.arraycopy(simbolos, 0, nuevo, 0, simbolos.length);
        nuevo[simbolos.length] = unidad;
        return nuevo;
    }

    private boolean siEsta(String lexema) {
        boolean resultado = false;
        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i].getLexema().equals(lexema)) {
//                System.out.println("no entra");
                resultado = true;
            }
        }
        return resultado;
    }

    public void generaError() {
        if (Arrays.asList(errores).isEmpty()) {
            String error = "Error en la linea: " + lector.obtenerLinea()
                    + " el error es: " + obtenerError();
//            System.out.println(error);
            errores = new String[1];
            errores[0] = error;
        } else {

            String error = "Error en la linea: " + lector.obtenerLinea()
                    + " el error es: " + obtenerError();
//            System.out.println(error);
            String[] nuevo = new String[errores.length + 1];
            System.arraycopy(errores, 0, nuevo, 0, errores.length);
            nuevo[errores.length] = error;
            errores = nuevo;
        }
        apuntadorB++;
        apuntadorA = apuntadorB;
//        System.out.println("se crea un token");
        if (apuntadorB >= linea.length()) {
            apuntadorB = linea.length() - 1;
            apuntadorA = linea.length() - 1;
            estado = 0;
            AnalizadorLexico();
        } else {

            estado = 0;
            Analiza();
        }
    }

    public void mostrarResultados() {
//        System.out.println("---------Tabla de errores------------");
//        for (int i = 0; i < errores.length; i++) {
//            System.out.println(errores[i]);
//        }
//        System.out.println("---------Tabla de Token-----------");
//        for (int i = 0; i < unidadesLexicas.length; i++) {
//            System.out.println(unidadesLexicas[i]);
//        }
//        System.out.println("---------Tabla de Simbolos-----------");
//        for (int i = 0; i < simbolos.length; i++) {
//            System.out.println(simbolos[i]);
//            
//        }
        String aux1 = "Simbolos:\n";
        for (int i = 0; i < simbolos.length; i++) {
            if ((i + 1) == simbolos.length) {
                aux1 += "Lexema--> " + simbolos[i].getLexema() + "| Clasificacion--> " + simbolos[i].getClasificacion();
            } else {
                aux1 += "Lexema--> " + simbolos[i].getLexema() + "| Clasificacion--> " + simbolos[i].getClasificacion() + "\n";
            }
        }
        String aux2 = "Palabras Reservadas:\n";
        for (int i = 0; i < palabrasReservadas.length; i++) {
            if ((i + 1) == palabrasReservadas.length) {
                aux2 += palabrasReservadas[i];
            } else {
                aux2 += palabrasReservadas[i] + "\n";
            }
        }
        String aux3 = "Tokens:\n";
        for (int i = 0; i < unidadesLexicas.length; i++) {
            if ((i + 1) == unidadesLexicas.length) {
                aux3 += unidadesLexicas[i].toString();
            } else {
                aux3 += unidadesLexicas[i].toString() + "\n";
            }
        }
        String aux4 = "Errores:\n";
        if (errores.length > 1) {
//            String aux3 = "Errores:\n";
            for (int i = 0; i < errores.length; i++) {
                if ((i + 1) == errores.length) {
                    aux4 += errores[i];
                } else {
                    aux4 += errores[i] + "\n";
                }
            }
        }
        String nombreArchivo = "src/Resultado/Ejecucion.txt";
        Escritor escr = new Escritor(nombreArchivo);
        escr.write("");
        escr.write(aux1);
        escr.write("");
        escr.write(aux2);
        escr.write("");
        escr.write(aux3);
        escr.write("");
        if (errores.length > 1) {
            escr.write(aux4);
        }
        escr.close();
    }

    private String obtenerError() {
        int f = apuntadorB;
        //Ubicar bien el puntero final para tomar todo el error
        boolean seguir = true;
        while (seguir) {
            f++;
            if (f >= linea.length()) {
                f--;
                seguir = false;
            } else if (linea.charAt(f) == ' ') {
                f--;
                seguir = false;
            } else {
                seguir = true;
            }
        }
        apuntadorB = f;

        //Tomar el error
        int i = apuntadorA;
        String retorno = "";
        if (i != f) {
            //size = (f - i) + 1;
            int size = (f - i) + 1;
            for (int j = 0; j < size; j++) {
                retorno += linea.charAt(i);
                i++;
            }
        } else {
            retorno += linea.charAt(apuntadorA);
        }
        return retorno;
    }

    private String obtenerLexema() {
        String retorno = "";
        int i = apuntadorA;
        int f = apuntadorB;
        if (i != f) {
            //size = (f - i) + 1;
            int size = (f - i) + 1;
            for (int j = 0; j < size; j++) {
                retorno += linea.charAt(i);
                i++;
            }
        } else {
            retorno += linea.charAt(apuntadorA);
        }
        return retorno;
    }
}
