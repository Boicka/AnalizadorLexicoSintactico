package Sintactico;

import Pila.*;
import Analizador.*;
import java.util.Arrays;
import Tablas.*;

public class AnalizadorSintactico {

    private int indexTerminal;
    private int indexNoTerminal;
    private NodeStack pila;
    private Analizador analizadorLexico;

    String[] noTerminales;
    String[] terminales;
    String[] ladoDerecho;
    int[][] matrizPredictiva;
    

    public AnalizadorSintactico(String ruta) {
        Tablas t = new Tablas();
        analizadorLexico = new Analizador(ruta);
        pila = new NodeStack();
        indexTerminal = 0;
        indexNoTerminal = 0;
        noTerminales = t.getNoTerminales();
        terminales = t.getTerminales();
        ladoDerecho = t.getLadoDerecho();
        matrizPredictiva = t.getMatrizPredictiva();
    }

    public void LlDriver() {
        System.out.println("Inicio de LlDriver");
        pila.push(noTerminales[0]);
        System.out.println("Ingresa el simbolo incial en la pila");
        String x = noTerminales[0];
        System.out.println("Asigna a x el simbolo en la parte alta de la pila");
        Token a = analizadorLexico.darToken();
        System.out.println("Asigna el token de entrada");
        while (!pila.isEmpty()) {
            System.out.println("La pila no esta vacia y entra al ciclo");
            if (Arrays.asList(noTerminales).contains(x)) {
                System.out.println("X es un simbolo no terminal");
                System.out.println("x es: " + x);
                System.out.println("Token: " + a.toString());
//                System.out.println("Predecir = " + predecir(x, a));
                if ("#".equals(a.getLexema()) && "#".equals(x)) {
                    pila.pop();
                    break;
                }
                if (predecir(x, a) != 0) {
                    pila.pop();
                    System.out.println("Se saca un elemento de la pila");
                    x = ladoDerecho[predecir(x, a)];
                    String[] aux = ladoDerecho[predecir(x, a)].split(" ");
                    System.out.println(aux.length);
                    //Asignar X 
                    if (aux.length <= 1) {
                        if (aux[0].isEmpty() == false) {
                            x = aux[0];
                            System.out.println("Remplazar x por la produccion: " + x);
                        } else {
                            x = (String) pila.top();
                            System.out.println("Remplazar x por la produccion: " + x);
                        }

                    } else {
                        x = aux[0];
                        System.out.println("Remplazar x por la produccion: " + x);
                    }
                    System.out.println("Se agrega a la pila los elementos del lado derecho de la producción");
                    //Agregar elementos de la pila
                    if (aux.length <= 1) {
                        if (aux[0].isEmpty() == false) {
                            pila.push(aux[0]);
                            System.out.println("Se ingresa a la pila: " + aux[0]);
                        }
                    } else {
                        for (int i = aux.length - 1; i >= 0; i--) {
                            pila.push(aux[i]);
                            System.out.println("Se ingresa a la pila: " + aux[i]);
                        }
                    }

                } else {
                    System.out.println("Error en x= " + x + " a= " + a);
                    break;
                }
            } /////////////////////////////////////////////////////////////////////////////////////////////////////
            else {
                System.out.println("X es un simbolo terminal");
                System.out.println("x es: " + x);
                System.out.println("Token: " + a.toString());
                if ("Palabra reservada".equals(a.getClasificacion()) || "Carácter simple".equals(a.getClasificacion())) {
                    //Here
                    if (x.equals(a.getLexema())) {
                        pila.pop();
//                        System.out.println("Se saca un elemento de la pila" + pila.pop());
                        a = analizadorLexico.darToken();
                        if (a == null) {
                            a = new Token("#", 333, "#");
                        }
                        x = (String) pila.top();
//                        System.out.println("El tope de la pila es tal = " + x);
                    } else {
                        System.out.println("Error en x= " + x + " a= " + a);
                        break;
                    }
                    //Here
                } else {
                    //Here
                    if (x.equals(a.getClasificacion())) {
                        pila.pop();
                        System.out.println("Se saca un elemento de la pila");
                        a = analizadorLexico.darToken();
                        if (a == null) {
                            a = new Token("#", 333, "#");
                        }
                        x = (String) pila.top();
                    } else {
                        System.out.println("Error en x= " + x + " a= " + a);
                        break;
                    }
                    //Here
                }

            }
        }
        if (pila.isEmpty() && "#".equals(a.getLexema()) && x.equals("#")) {
            System.out.println("El programa es correcto");
        } else {
            System.out.println("Programa es incorrecto");
//            System.out.println(pila.isEmpty());
        }
    }

    private int predecir(String x, Token a) {
        int p = 0;

        //Identificar valor de x para Index no terminal
        for (int i = 0; i < noTerminales.length; i++) {
            if (noTerminales[i].equals(x)) {
                indexNoTerminal = i;
            }
        }//Fin for Index no terminal

        //Identificar valor de a para Index terminal
        if ("Palabra reservada".equals(a.getClasificacion()) || "Carácter simple".equals(a.getClasificacion())) {
            for (int j = 0; j < terminales.length; j++) {
                if (terminales[j].equals(a.getLexema())) {
                    indexTerminal = j;
                }
            }
        }//Fin if Palabra reservada & caracter simple
        else {
            for (int j = 0; j < terminales.length; j++) {
                if (terminales[j].equals(a.getClasificacion())) {
                    indexTerminal = j;
                }
            }
        }
        //Fin for Index terminal

        p = matrizPredictiva[indexNoTerminal][indexTerminal];

        return p;
    }

}
