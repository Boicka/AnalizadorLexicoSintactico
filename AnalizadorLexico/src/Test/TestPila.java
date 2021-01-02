/*
TestPila.java
Instituto Tecnológico de León
Ingenieria en sistemas computacionales
Lenguajes y autómatas 1
Lunes y Miercoles 8:45 - 10:25
Viernes 8:45 - 9:35
Alumnos: Héctor Medel Negrete
         Juan de Dios Yebra Navarro
Version 1.0
 */
package Test;
import Pila.*;

public class TestPila {

    public static void main(String[] args) {
    NodeStack pila = new NodeStack();
    
    pila.push("Hola");
    pila.push(12.09);
    pila.push(10);
    
    System.out.println(pila.top());
    System.out.println(pila.pop());

    }
}
