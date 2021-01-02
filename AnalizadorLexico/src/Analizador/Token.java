/*
Token.java
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

public class Token {

    protected String lexema;
    protected int atributo;
    protected String clasificacion;
    
    public Token(){
    }

    public Token(String lexema, int atributo, String clasificacion) {
        this.lexema = lexema;
        this.atributo = atributo;
        this.clasificacion = clasificacion;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getAtributo() {
        return atributo;
    }

    public void setStributo(int atributo) {
        this.atributo = atributo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
        return "Lexema -> " + lexema + "| Clasificacion -> " + clasificacion + "| Atributo -> " + atributo;
    }
}
